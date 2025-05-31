package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BreakCommand implements InternalCommand
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;
    private final LocaleMessageService localeMessageService;

    @Autowired
    public BreakCommand(SendBotMessageService sendBotMessageService, @Lazy TimerService timerService, LocaleMessageService localeMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
        this.localeMessageService = localeMessageService;
    }

    @Override
    public void execute(String chatId, Long userId)
    {
        int breakTime = timerService.getBreakTime(chatId);
        String message = localeMessageService.getMessage("break", userId);
        String richMessage = String.format(message, breakTime);
        sendBotMessageService.sendMessage(chatId, richMessage);
    }
}
