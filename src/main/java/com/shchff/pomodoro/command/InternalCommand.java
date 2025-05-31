package com.shchff.pomodoro.command;

public interface InternalCommand
{
    void execute(String chatId, Long userId);
}
