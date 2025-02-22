package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.timer.TimerResult;
import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public final static String STOP_POMODORO_MESSAGE_SUCCESS = "\uD83D\uDC4D Работа окончена, ты молодец!";
    public final static String STOP_POMODORO_MESSAGE_FAILURE = "\uD83E\uDD37 Тебе нечего останавливать";

    public StopPomodoroCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        TimerResult result = timerService.stopPomodoro(chatId);
        if (result == TimerResult.SUCCESS)
        {
            sendBotMessageService.sendMessage(chatId, STOP_POMODORO_MESSAGE_SUCCESS);
        }
        else
        {
            sendBotMessageService.sendMessage(chatId, STOP_POMODORO_MESSAGE_FAILURE);
        }
    }
}
