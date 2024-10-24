package com.shchff.pomodoro_bot.command;

import com.shchff.pomodoro_bot.service.SendBotMessageService;

import java.util.HashMap;
import java.util.Map;

import static com.shchff.pomodoro_bot.command.CommandName.*;

public class CommandContainer
{
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = new HashMap<>();
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(START_POMODORO.getCommandName(), new StartPomodoroCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandMap.put(ABOUT.getCommandName(), new AboutCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
