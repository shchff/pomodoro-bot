package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.SET_LANGUAGE;

@Component
@RequiredArgsConstructor
public class SetLanguageCommand implements Command
{
    private final UserPreferencesService userPreferencesService;
    private final SendBotMessageService sendBotMessageService;

    @Override
    public void execute(Update update)
    {
        String callbackData = update.getCallbackQuery().getData();
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        Long userId = update.getCallbackQuery().getFrom().getId();

        String selectedLang = callbackData.substring(5);
        userPreferencesService.saveUserLocale(userId, selectedLang);
        sendBotMessageService.sendMessage(chatId, "✅ Language updated to: " + selectedLang.toUpperCase());
    }

    @Override
    public String getCommandName()
    {
        return SET_LANGUAGE.getCommandName();
    }
}
