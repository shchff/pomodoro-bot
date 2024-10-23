package com.shchff.pomodoro_bot.command;

public enum CommandName
{
    START("/start"),
    WORK("/work"),
    HELP("/help"),
    NO("nocommand");

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
