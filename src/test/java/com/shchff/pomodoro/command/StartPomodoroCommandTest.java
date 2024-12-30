package com.shchff.pomodoro.command;

import org.junit.jupiter.api.DisplayName;

import static com.shchff.pomodoro.command.CommandName.START_POMODORO;

@DisplayName("Unit-level testing for UnknownCommand")
public class StartPomodoroCommandTest extends AbstractCommandTest
{

    @Override
    String getCommandName()
    {
        return START_POMODORO.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return StartPomodoroCommand.START_POMODORO_MESSAGE_SUCCESS;
    }

    @Override
    Command getCommand()
    {
        return new StartPomodoroCommand(sendBotMessageService, timerService);
    }
}
