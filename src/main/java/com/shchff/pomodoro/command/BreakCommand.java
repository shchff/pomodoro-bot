package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.LocaleMessageService;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Locale;

import static com.shchff.pomodoro.command.CommandName.BREAK;

@Component
public class BreakCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;
    private final TimerService timerService;
    private final LocaleMessageService localeMessageService;

    @Autowired
    public BreakCommand(SendBotMessageService sendBotMessageService, @Lazy TimerService timerService, LocaleMessageService localeMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
        this.timerService = timerService;
        this.localeMessageService = localeMessageService;
    }

    @Override
    public void execute(Update update)
    {
        String chatId = CommandUtils.getChatId(update).toString();
        int breakTime = timerService.getBreakTime(chatId);
        Locale userLocale = CommandUtils.getUserLocale(update);
        String message = localeMessageService.getMessage("break", userLocale);
        String richMessage = String.format(message, breakTime);
        sendBotMessageService.sendMessage(chatId, richMessage);
    }

    @Override
    public String getCommandName()
    {
        return BREAK.getCommandName();
    }
}
