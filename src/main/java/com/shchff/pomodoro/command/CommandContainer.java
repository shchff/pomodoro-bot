package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.timer.TimerService;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import static com.shchff.pomodoro.command.CommandName.*;

public class CommandContainer
{
    private final Map<String, Command> commandMap;
    private final Command unknownCommand;
    @Getter
    private final SetBreakTimeCommand setBreakTimeCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TimerService timerService)
    {
        this.setBreakTimeCommand = new SetBreakTimeCommand(timerService);
        commandMap = new HashMap<>();
        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(START_POMODORO.getCommandName(), new StartPomodoroCommand(sendBotMessageService, timerService));
        commandMap.put(STOP_POMODORO.getCommandName(), new StopPomodoroCommand(sendBotMessageService, timerService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandMap.put(ABOUT.getCommandName(), new AboutCommand(sendBotMessageService));
        commandMap.put(STATUS.getCommandName(), new StatusCommand(sendBotMessageService, timerService));

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier)
    {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
