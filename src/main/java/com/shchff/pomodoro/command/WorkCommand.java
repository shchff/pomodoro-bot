package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkCommand implements InternalCommand
{
    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(String chatId, Long userId)
    {
        String message = localeMessageService.getMessage("work", userId);
        sendBotMessageService.sendMessage(chatId, message);
    }
}
