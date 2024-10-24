package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.shchff.pomodoro.command.CommandName.*;

public class HelpCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String HELP_MESSAGE = String.format("""
            📋 Доступные команды:
            
            ▶️ %s - Запустить стандартный цикл Помидора (25 минут работы и 5 минут отдыха)
            ⏱ /setWorkTime [минуты] - Настроить продолжительность рабочего интервала
            ☕️ /setBreakTime [минуты] - Настроить продолжительность короткого перерыва
            🕒 /setLongBreakTime [минуты] - Настроить продолжительность длинного перерыва (после 4 циклов)
            ⏹ /stopPomodoro - Остановить текущий таймер
            🔄 /status - Узнать статус текущего таймера
            ℹ️ /help - Показать это сообщение
            ❓ /about - Описание метода Помидора
            
            🚀 Начинай, когда будешь готов!""", START_POMODORO.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }


    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}