package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import com.shchff.pomodoro.service.timer.TimerState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.STATUS;

@Component
@RequiredArgsConstructor
public class StatusCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;
    private final LocaleMessageService localeMessageService;

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        Long userId = CommandUtils.getUserIdFromMessage(update.getMessage());
        TimerState state = timerService.getCurrentState(chatId);

        String message;
        if (state == TimerState.OFF)
        {
            message = localeMessageService.getMessage("status.off", userId);
        }
        else
        {
            int pomodoroCount = timerService.getSessionCount(chatId);

            if (state == TimerState.WORK)
            {
                message = localeMessageService.getMessage("status.work", userId);
            }
            else
            {
                message = localeMessageService.getMessage("status.break", userId);
            }

            message = String.format(message, pomodoroCount);
        }

        sendBotMessageService.sendMessage(chatId, message);
    }

    @Override
    public String getCommandName()
    {
        return STATUS.getCommandName();
    }
}
