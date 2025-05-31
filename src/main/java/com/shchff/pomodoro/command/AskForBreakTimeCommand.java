package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.ASK_FOR_BREAK_TIME;

@Component
@RequiredArgsConstructor
public class AskForBreakTimeCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        Locale userLocale = CommandUtils.getUserLocale(update);
        String message = localeMessageService.getMessage("askForBreakTime", userLocale);
        sendBotMessageService.sendMessageWithReplyKeyboard(chatId, message, getInlineKeyboard());
    }

    private ReplyKeyboard getInlineKeyboard()
    {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = List.of(
                List.of(createButton("5", "break_time: 5"), createButton("10", "break_time: 10")),
                List.of(createButton("15", "break_time: 15"), createButton("20", "break_time: 20"))
        );
        markup.setKeyboard(rows);
        return markup;
    }

    private InlineKeyboardButton createButton(String text, String data)
    {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(data);
        return button;
    }

    @Override
    public String getCommandName()
    {
        return ASK_FOR_BREAK_TIME.getCommandName();
    }
}
