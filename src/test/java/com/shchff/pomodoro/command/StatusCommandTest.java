package com.shchff.pomodoro.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.shchff.pomodoro.command.CommandName.STATUS;

@DisplayName("Unit-level testing for StatusCommand")
public class StatusCommandTest extends AbstractCommandTest
{
    @Override
    String getCommandName()
    {
        return STATUS.getCommandName();
    }

    @Override
    String getCommandMessage()
    {
        return StatusCommand.TIMER_OFF_MESSAGE;
    }

    @Override
    Command getCommand()
    {
        return new StatusCommand(sendBotMessageService, timerService);
    }

    @Test
    public void shouldShowWorkingState()
    {

    }
}
