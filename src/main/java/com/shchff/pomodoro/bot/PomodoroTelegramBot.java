package com.shchff.pomodoro.bot;

import com.shchff.pomodoro.command.CommandContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.NO;

@Component
public class PomodoroTelegramBot extends TelegramLongPollingBot
{
    public static String COMMAND_PREFIX = "/";

    private CommandContainer commandContainer;

    @Value("${bot.username}")
    private String username;

    public PomodoroTelegramBot(@Value("${bot.token}") String botToken)
    {
        super(botToken);
    }

    @Autowired
    public void setCommandContainer(CommandContainer commandContainer)
    {
        this.commandContainer = commandContainer;
    }

    @Override
    public void onUpdateReceived(Update update)
    {
        if (update.hasCallbackQuery())
        {
            String callbackData = update.getCallbackQuery().getData();
            if (callbackData.startsWith("break_time:"))
            {
                commandContainer.getSetBreakTimeCommand().execute(update);
            }
        }

        if (update.hasMessage() && update.getMessage().hasText())
        {
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
        return username;
    }
}
