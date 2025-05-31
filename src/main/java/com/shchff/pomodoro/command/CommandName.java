package com.shchff.pomodoro.command;

import lombok.Getter;

@Getter
public enum CommandName
{
    ABOUT("/about"),
    ASK_FOR_BREAK_TIME("askforbreaktime"),
    BREAK("break"),
    HELP("/help"),
    NO("nocommand"),
    START("/start"),
    SET_BREAK_TIME("setbreaktime"),
    START_POMODORO("/start_pomodoro"),
    STATUS("/status"),
    STOP_POMODORO("/stop_pomodoro"),
    UNKNOWN("unknown"),
    WORK("work");

    private final String commandName;

    CommandName(String commandName)
    {
        this.commandName = commandName;
    }
}
