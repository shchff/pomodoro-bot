package com.shchff.pomodoro.command;

import lombok.Getter;

@Getter
public enum CommandName
{
    ABOUT("/about"),
    HELP("/help"),
    NO("nocommand"),
    START("/start"),
    START_POMODORO("/start_pomodoro"),
    STATUS("/status"),
    STOP_POMODORO("/stop_pomodoro");

    private final String commandName;

    CommandName(String commandName)
    {
        this.commandName = commandName;
    }
}
