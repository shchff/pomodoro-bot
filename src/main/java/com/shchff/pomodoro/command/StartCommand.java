package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.*;

@Component
@RequiredArgsConstructor
public class StartCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        Long userId = CommandUtils.getUserIdFromMessage(update.getMessage());
        String message = localeMessageService.getMessage("start", userId);
        String richMessage = buildMessage(message);
        sendBotMessageService.sendMessage(chatId, richMessage);
    }

    private String buildMessage(String message)
    {
        return String.format(message,
                START_POMODORO.getCommandName(),
                HELP.getCommandName());
    }

    @Override
    public String getCommandName()
    {
        return START.getCommandName();
    }
}
