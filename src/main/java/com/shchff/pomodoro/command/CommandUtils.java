package com.shchff.pomodoro.command;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

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

    public static Long getUserIdFromMessage(Message message)
    {
        return message.getFrom().getId();
    }

    public static Long getUserIdFromCallbackQuery(CallbackQuery callbackQuery)
    {
        return callbackQuery.getFrom().getId();
    }

}
