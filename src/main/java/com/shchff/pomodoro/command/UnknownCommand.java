package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.HELP;
import static com.shchff.pomodoro.command.CommandName.UNKNOWN;

@Component
@RequiredArgsConstructor
public class UnknownCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        Long userId = CommandUtils.getUserIdFromMessage(update.getMessage());
        String message = localeMessageService.getMessage("unknown", userId);
        String richMessage = buildMessage(message);
        sendBotMessageService.sendMessage(chatId, richMessage);
    }

    private String buildMessage(String message)
    {
        return String.format(message,
                HELP.getCommandName());
    }

    @Override
    public String getCommandName()
    {
        return UNKNOWN.getCommandName();
    }
}