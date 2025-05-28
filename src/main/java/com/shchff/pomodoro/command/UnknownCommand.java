package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.HELP;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = String.format("Не понимаю вас \uD83D\uDE1F, напишите %s чтобы узнать что я понимаю.", HELP.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_MESSAGE);
    }
}