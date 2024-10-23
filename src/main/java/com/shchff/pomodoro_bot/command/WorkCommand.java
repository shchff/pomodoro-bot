package com.shchff.pomodoro_bot.command;

import com.shchff.pomodoro_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class WorkCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String WORK_MESSAGE = "Начало работы";

    public WorkCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), WORK_MESSAGE);
    }
}