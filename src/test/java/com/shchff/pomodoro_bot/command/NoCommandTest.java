package com.shchff.pomodoro_bot.command;

import org.junit.jupiter.api.DisplayName;
import static com.shchff.pomodoro_bot.command.CommandName.NO;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NoCommand.NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
