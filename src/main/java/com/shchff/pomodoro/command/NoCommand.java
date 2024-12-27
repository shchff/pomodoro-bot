package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.shchff.pomodoro.command.CommandUtils.getChatId;

public class NoCommand implements Command
{

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
            + "Чтобы посмотреть список команд введите /help";

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