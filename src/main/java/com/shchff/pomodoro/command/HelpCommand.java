package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.*;
import static com.shchff.pomodoro.command.CommandUtils.getChatId;

public class HelpCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String HELP_MESSAGE = String.format("""
            📋 Доступные команды:
            
            ▶️ %s - Запустить стандартный цикл Помидора (25 минут работы и 5 минут отдыха)
            ⏹ %s - Остановить текущий таймер
            🔄 %s - Узнать статус текущего таймера
            ℹ️ %s - Показать это сообщение
            ❓ %s - Описание метода Помидора
            
            🚀 Начинай, когда будешь готов!""",
            START_POMODORO.getCommandName(),
            STOP_POMODORO.getCommandName(),
            STATUS.getCommandName(),
            HELP.getCommandName(),
            ABOUT.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(getChatId(update).toString(), HELP_MESSAGE);
    }
}