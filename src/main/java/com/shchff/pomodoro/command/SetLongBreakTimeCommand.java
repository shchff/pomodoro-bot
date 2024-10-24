package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SetLongBreakTimeCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public SetLongBreakTimeCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update)
    {

    }
}
