package com.shchff.pomodoro_bot.command;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest extends AbstractCommandTest
{
    @Override
    String getCommandName()
    {
        return "/ankjednzkxjdnf";
    }

    @Override
    String getCommandMessage()
    {
        return UnknownCommand.UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand()
    {
        return new UnknownCommand(sendBotMessageService);
    }
}
