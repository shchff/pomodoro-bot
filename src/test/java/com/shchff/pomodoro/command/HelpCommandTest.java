package com.shchff.pomodoro.command;

import org.junit.jupiter.api.DisplayName;

import static com.shchff.pomodoro.command.CommandName.HELP;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest
{

    @Override
    String getCommandName()
    {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return HelpCommand.HELP_MESSAGE;
    }

    @Override
    Command getCommand()
    {
        return new HelpCommand(sendBotMessageService);
    }
}
