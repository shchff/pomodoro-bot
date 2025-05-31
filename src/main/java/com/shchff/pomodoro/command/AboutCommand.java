package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

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
        Long userId = CommandUtils.getUserIdFromMessage(update.getMessage());
        String chatId = CommandUtils.getChatId(update).toString();
        String message = localeMessageService.getMessage("about", userId);
        sendBotMessageService.sendMessage(chatId, message);
    }

    @Override
    public String getCommandName()
    {
        return ABOUT.getCommandName();
    }
}
