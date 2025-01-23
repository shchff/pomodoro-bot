package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.timer.TimerResult;
import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public final static String START_POMODORO_MESSAGE_SUCCESS = "Помодоро-таймер запущен!" +
            "\n" +
            "Продуктивной работы!";

    public final static String START_POMODORO_MESSAGE_FAILURE = "В данный момент уже запущена сессия";


    public StartPomodoroCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }


    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        TimerResult result = timerService.startPomodoro(chatId);

        if (result == TimerResult.SUCCESS)
        {
            sendBotMessageService.sendMessage(chatId, START_POMODORO_MESSAGE_SUCCESS);
        }
        else
        {
            sendBotMessageService.sendMessage(chatId, START_POMODORO_MESSAGE_FAILURE);
        }
    }
}