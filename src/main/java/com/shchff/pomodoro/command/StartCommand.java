package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.HELP;
import static com.shchff.pomodoro.command.CommandName.START_POMODORO;

public class StartCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = String.format("""
            👋 Привет! Я Pomodoro Bot, и я помогу тебе лучше управлять временем и повысить продуктивность.
            
            🍅 Метод Помидора: 25 минут работы и 5 минут отдыха. После четырёх таких циклов я предложу тебе более длинный перерыв.
            👉 Чтобы начать, просто используй команду %s.
            ℹ️ Для получения всех доступных команд используй %s.""", START_POMODORO.getCommandName(), HELP.getCommandName());

    public StartCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        sendBotMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
