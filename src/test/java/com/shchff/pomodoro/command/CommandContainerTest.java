package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

@DisplayName("Unit-level testing for CommandContainer")
public class CommandContainerTest
{
    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TimerService timerService = Mockito.mock(TimerService.class);
        commandContainer = new CommandContainer(sendBotMessageService, timerService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
            .forEach(commandName -> {
                Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
            });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";

        //when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
