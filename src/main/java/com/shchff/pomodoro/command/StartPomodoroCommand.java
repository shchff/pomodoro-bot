package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.timer.TimerResult;
import com.shchff.pomodoro.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.START_POMODORO;

@Component
@RequiredArgsConstructor
public class StartPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        TimerResult result = timerService.startPomodoro(chatId);
        Locale userLocale = CommandUtils.getUserLocale(update);

        String message;
        if (result == TimerResult.SUCCESS)
        {
            message = localeMessageService.getMessage("startPomodoro.success", userLocale);
        }
        else
        {
            message = localeMessageService.getMessage("startPomodoro.failure", userLocale);
        }

        sendBotMessageService.sendMessage(chatId, message);
    }

    @Override
    public String getCommandName()
    {
        return START_POMODORO.getCommandName();
    }
}