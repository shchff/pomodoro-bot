package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String START_POMODORO_MESSAGE = "Начало работы";

    public StartPomodoroCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_POMODORO_MESSAGE);
    }
}