package com.shchff.pomodoro.command;

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
}
