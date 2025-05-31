package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.timer.TimerResult;
import com.shchff.pomodoro.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.STOP_POMODORO;

@Component
@RequiredArgsConstructor
public class StopPomodoroCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        TimerResult result = timerService.stopPomodoro(chatId);
        Locale userLocale = CommandUtils.getUserLocale(update);

        String message;
        if (result == TimerResult.SUCCESS)
        {
            message = localeMessageService.getMessage("stopPomodoro.success", userLocale);
        }
        else
        {
            message = localeMessageService.getMessage("stopPomodoro.failure", userLocale);
        }

        sendBotMessageService.sendMessage(chatId, message);
    }

    @Override
    public String getCommandName()
    {
        return STOP_POMODORO.getCommandName();
    }
}
