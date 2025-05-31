package com.shchff.pomodoro.command;

import lombok.Getter;

@Getter
public enum CommandName
{
    ABOUT("/about"),
    HELP("/help"),
    LANGUAGE("/language"),
    NO("nocommand"),
    START("/start"),
    SET_BREAK_TIME("setbreaktime"),
    SET_LANGUAGE("setlanguage"),
    START_POMODORO("/start_pomodoro"),
    STATUS("/status"),
    STOP_POMODORO("/stop_pomodoro"),
    UNKNOWN("unknown");

    private final String commandName;

    CommandName(String commandName)
    {
        this.commandName = commandName;
    }
}
