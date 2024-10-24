package com.shchff.pomodoro_bot.command;

import org.junit.jupiter.api.DisplayName;

import static com.shchff.pomodoro_bot.command.CommandName.START;

@DisplayName("Unit-level testing for StartCommand")
public class StartCommandTest extends AbstractCommandTest
{

    @Override
    String getCommandName()
    {
        return START.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return StartCommand.START_MESSAGE;
    }

    @Override
    Command getCommand()
    {
        return new StartCommand(sendBotMessageService);
    }
}
