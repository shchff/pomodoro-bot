package com.shchff.pomodoro_bot.bot;

import com.shchff.pomodoro_bot.command.CommandContainer;
import com.shchff.pomodoro_bot.config.BotConfig;
import com.shchff.pomodoro_bot.service.SendBotMessageServiceImpl;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro_bot.command.CommandName.NO;

@Component
public class PomodoroTelegramBot extends TelegramLongPollingBot
{
    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;
    private final BotConfig config;

    public PomodoroTelegramBot(BotConfig config)
    {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX))
            {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            }
            else
            {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername()
    {
        return config.getUsername();
    }

    @Override
    public String getBotToken()
    {
        return config.getToken();
    }
}
