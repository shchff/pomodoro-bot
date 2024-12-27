package com.shchff.pomodoro.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtils
{
    public static Long getChatId(Update update)
    {
        return update.getMessage().getChatId();
    }

    public static Long getMessage(Update update)
    {
        return update.getMessage().getChatId();
    }
}
