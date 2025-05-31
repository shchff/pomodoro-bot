package com.shchff.pomodoro.command;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandContainer
{
    private final Map<String, Command> commandMap = new HashMap<>();
    private final Command unknownCommand;
    @Getter
    private final SetBreakTimeCommand setBreakTimeCommand;
    @Getter
    private final SetLanguageCommand setLanguageCommand;

    @Autowired
    public CommandContainer(List<Command> commands, SetBreakTimeCommand setBreakTimeCommand,
                            SetLanguageCommand setLanguageCommand, UnknownCommand unknownCommand)
    {
        this.setBreakTimeCommand = setBreakTimeCommand;
        this.setLanguageCommand = setLanguageCommand;
        this.unknownCommand = unknownCommand;

        for (Command command : commands)
        {
            if (!(command instanceof UnknownCommand) && !(command instanceof SetBreakTimeCommand)
                && !(command instanceof SetLanguageCommand))
            {
                String commandName = command.getCommandName();
                commandMap.put(commandName, command);
            }
        }
    }

    public Command retrieveCommand(String commandIdentifier)
    {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
