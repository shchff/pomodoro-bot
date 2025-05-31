package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.TimerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.SET_BREAK_TIME;

@Component
@RequiredArgsConstructor
public class SetBreakTimeCommand implements Command
{
    private final TimerService timerService;

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        int break_time = Integer.parseInt(update.getCallbackQuery().getData().split(" ")[1]);
        timerService.setBreakTime(chatId, break_time);
    }

    @Override
    public String getCommandName()
    {
        return SET_BREAK_TIME.getCommandName();
    }
}
