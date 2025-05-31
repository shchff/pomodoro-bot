package com.shchff.pomodoro.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.Locale;

public class CommandUtils
{
    public static Long getChatId(Update update)
    {
        if (update.hasMessage())
        {
            return update.getMessage().getChatId();
        }
        else if (update.hasCallbackQuery())
        {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return -1L;
    }

    public static String getMessage(Update update)
    {
        return update.getMessage().toString();
    }

    public static Locale getUserLocale(Update update)
    {
        String languageCode = update.getMessage().getFrom().getLanguageCode();
        return languageCode != null ? new Locale(languageCode) : Locale.ENGLISH;
    }

    public static Update buildUpdateWithChatId(String chatId)
    {
        Update update = new Update();
        Message message = new Message();
        Chat chat = new org.telegram.telegrambots.meta.api.objects.Chat();
        chat.setId(Long.parseLong(chatId));
        message.setChat(chat);
        update.setMessage(message);
        return update;
    }
}
