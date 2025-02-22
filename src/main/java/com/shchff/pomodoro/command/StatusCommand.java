package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import com.shchff.pomodoro.service.timer.TimerState;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatusCommand implements Command
{
    public final static String TIMER_OFF_MESSAGE = "\uD83D\uDE34 В данный момент таймер не запущен";
    public final static String TIMER_WORK_MESSAGE = "⛏️ Работа\n\nНомер текущего раунда - %d";
    public final static String TIMER_BREAK_MESSAGE = "⏸️ Перерыв\n\nНомер текущего раунда - %d";

    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public StatusCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }

    private String formMessage(int pomodoroCount, TimerState state)
    {
        String message;

        if (state == TimerState.WORK)
        {
            message = TIMER_WORK_MESSAGE;
        }
        else
        {
            message = TIMER_BREAK_MESSAGE;
        }

        return String.format(message, pomodoroCount);
    }

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        TimerState state = timerService.getCurrentState(chatId);
        if (state == TimerState.OFF)
        {
            sendBotMessageService.sendMessage(chatId, TIMER_OFF_MESSAGE);
        }
        else
        {
            int pomodoroCount = timerService.getSessionCount(chatId);
            sendBotMessageService.sendMessage(chatId, formMessage(pomodoroCount, state));
        }
    }
}
