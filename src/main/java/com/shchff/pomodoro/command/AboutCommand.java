package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.ABOUT;

@Component
@RequiredArgsConstructor
public class AboutCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        Locale userLocale = CommandUtils.getUserLocale(update);
        String message = localeMessageService.getMessage("about", userLocale);
        sendBotMessageService.sendMessage(chatId, message);
    }

    @Override
    public String getCommandName()
    {
        return ABOUT.getCommandName();
    }
}
