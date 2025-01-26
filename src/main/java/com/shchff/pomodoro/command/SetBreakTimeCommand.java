package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.TimerService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class SetBreakTimeCommand implements Command
{
    private final TimerService timerService;

    public SetBreakTimeCommand(TimerService timerService)
    {
        this.timerService = timerService;
    }

    @Override
    public void execute(Update update)
    {
        String chatId = String.valueOf(CommandUtils.getChatId(update));
        int break_time = Integer.parseInt(update.getCallbackQuery().getData().split(" ")[1]);
        
        timerService.setBreakTime(chatId, break_time);
    }
}
