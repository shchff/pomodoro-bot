package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.HELP;
import static com.shchff.pomodoro.command.CommandUtils.getChatId;

public class NoCommand implements Command
{

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = String.format("Я поддерживаю команды, начинающиеся со слеша(/)\n\nЧтобы посмотреть список команд введите %s", HELP.getCommandName());

    public NoCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(getChatId(update).toString(), NO_MESSAGE);
    }
}