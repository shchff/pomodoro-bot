package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatusCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;

    public StatusCommand(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
    }

    @Override
    public void execute(Update update)
    {

    }
}
