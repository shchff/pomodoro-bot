package com.shchff.pomodoro_bot.command;

import com.shchff.pomodoro_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SetWorkTimeCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public SetWorkTimeCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update)
    {

    }
}
