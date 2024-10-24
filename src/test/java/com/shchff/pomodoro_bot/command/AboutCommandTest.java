package com.shchff.pomodoro_bot.command;

import org.junit.jupiter.api.DisplayName;

import static com.shchff.pomodoro_bot.command.CommandName.ABOUT;

@DisplayName("Unit-level testing for NoCommand")
public class AboutCommandTest extends AbstractCommandTest
{

    @Override
    String getCommandName()
    {
        return ABOUT.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return AboutCommand.ABOUT_MESSAGE;
    }

    @Override
    Command getCommand()
    {
        return new AboutCommand(sendBotMessageService);
    }
}
