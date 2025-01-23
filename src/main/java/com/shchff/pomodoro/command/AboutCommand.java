package com.shchff.pomodoro.command;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.shchff.pomodoro.command.CommandUtils.getChatId;

public class AboutCommand implements Command
{
    private final SendBotMessageService sendBotMessageService;

    public final static String ABOUT_MESSAGE = "🍅 Метод Помидора — это техника управления временем, разработанная Франческо Чирилло в конце 1980-х годов.\n" +
            "\n" +
            "📚 Основные принципы:\n" +
            "1️⃣ Разделяй работу на короткие 25-минутные интервалы (они называются «помидоры»), после которых следуют короткие перерывы по 5 минут.\n" +
            "2️⃣ После четырёх таких «помидоров» делай более длинный перерыв в диапозоне от 5 до 25 минут.\n" +
            "\n" +
            "🎯 Цель метода — помочь тебе сконцентрироваться, избежать выгорания и повысить продуктивность.";

    public AboutCommand(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update)
    {
        sendBotMessageService.sendMessage(getChatId(update).toString(), ABOUT_MESSAGE);
    }
}
