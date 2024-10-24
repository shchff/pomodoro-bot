package com.shchff.pomodoro_bot.command;

import com.shchff.pomodoro_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro_bot.command.CommandName.HELP;
import static com.shchff.pomodoro_bot.command.CommandName.START_POMODORO;

public class StartCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = String.format("👋 Привет! Я Pomodoro Bot, и я помогу тебе лучше управлять временем и повысить продуктивность.\n" +
            "🍅 Метод Помидора: 25 минут работы и 5 минут отдыха. После четырёх таких циклов я предложу тебе более длинный перерыв на 15-30 минут.\n" +
            "⚙️ Ты можешь настроить таймер под себя с помощью команд или использовать стандартные настройки.\n" +
            "👉 Чтобы начать, просто используй команду %s.\n" +
            "ℹ️ Для получения всех доступных команд используй %s.", START_POMODORO.getCommandName(), HELP.getCommandName());

    public StartCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
