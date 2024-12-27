package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public final static String START_POMODORO_MESSAGE = "Начало работы";

    public StartPomodoroCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }


    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        timerService.startPomodoro(chatId);
        sendBotMessageService.sendMessage(chatId, START_POMODORO_MESSAGE);
    }
}