package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public final static String STOP_POMODORO_MESSAGE = "Окончание работы";

    public StopPomodoroCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        timerService.stopPomodoro(chatId);
        sendBotMessageService.sendMessage(chatId, STOP_POMODORO_MESSAGE);
    }
}
