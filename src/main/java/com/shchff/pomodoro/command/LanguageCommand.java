package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.shchff.pomodoro.command.CommandName.LANGUAGE;

@Component
@RequiredArgsConstructor
public class LanguageCommand implements Command
{

    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update) {
        String chatId = CommandUtils.getChatId(update).toString();
        Long userId = CommandUtils.getUserIdFromMessage(update.getMessage());
        String message = localeMessageService.getMessage("selectLanguage", userId);
        sendBotMessageService.sendMessageWithReplyKeyboard(chatId, message, buildInlineKeyboard());
    }

    @Override
    public String getCommandName()
    {
        return LANGUAGE.getCommandName();
    }

    private InlineKeyboardMarkup buildInlineKeyboard()
    {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(createButton("\uD83C\uDDFA\uD83C\uDDF8 English", "lang_en"));
        row1.add(createButton("\uD83C\uDDF7\uD83C\uDDFA Русский", "lang_ru"));

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(createButton("\uD83C\uDDEA\uD83C\uDDF8 Español", "lang_es"));
        row2.add(createButton("\uD83C\uDDE9\uD83C\uDDEA Deutsch", "lang_de"));

        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(createButton("\uD83C\uDDF5\uD83C\uDDF9 Português", "lang_pt"));

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(rows);
        return markup;
    }

    private InlineKeyboardButton createButton(String text, String callbackData)
    {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}
