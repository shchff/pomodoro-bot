package com.shchff.pomodoro.command;

public enum CommandName
{
    ABOUT("/about"),
    HELP("/help"),
    NO("nocommand"),
    SET_BREAK_TIME("/setBreakTime"),
    SET_LONG_BREAK_TIME("/setLongBreakTime"),
    SET_WORK_TIME("/setWorkTime"),
    START("/start"),
    START_POMODORO("/startPomodoro"),
    STATUS("/status"),
    STOP_POMODORO("/stopPomodoro");

    private final String commandName;

    CommandName(String commandName)
    {
        this.commandName = commandName;
    }

    public String getCommandName()
    {
        return commandName;
    }
}
