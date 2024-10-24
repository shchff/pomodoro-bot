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
        commandMap.put(SET_WORK_TIME.getCommandName(), new SetWorkTimeCommand(sendBotMessageService));
        commandMap.put(SET_BREAK_TIME.getCommandName(), new SetBreakTimeCommand(sendBotMessageService));
        commandMap.put(SET_LONG_BREAK_TIME.getCommandName(), new SetLongBreakTimeCommand(sendBotMessageService));
        commandMap.put(STATUS.getCommandName(), new StatusCommand(sendBotMessageService));
        commandMap.put(STOP_POMODORO.getCommandName(), new StopPomodoroCommand(sendBotMessageService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
