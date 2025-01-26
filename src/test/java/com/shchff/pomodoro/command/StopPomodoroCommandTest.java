package com.shchff.pomodoro.command;

import org.junit.jupiter.api.DisplayName;

import static com.shchff.pomodoro.command.CommandName.STOP_POMODORO;

@DisplayName("Unit-level testing for StopPomodoroCommand")
public class StopPomodoroCommandTest extends AbstractCommandTest
{
    @Override
    String getCommandName()
    {
        return STOP_POMODORO.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return StopPomodoroCommand.STOP_POMODORO_MESSAGE_FAILURE;
    }

    @Override
    Command getCommand()
    {
        return new StopPomodoroCommand(sendBotMessageService, timerService);
    }
}
