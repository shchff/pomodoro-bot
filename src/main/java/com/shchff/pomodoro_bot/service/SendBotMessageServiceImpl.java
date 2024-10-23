package com.shchff.pomodoro_bot.service;

import com.shchff.pomodoro_bot.bot.PomodoroTelegramBot;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService
{
    private final PomodoroTelegramBot pomodoroBot;

    @Autowired
    public SendBotMessageServiceImpl(PomodoroTelegramBot pomodoroBot)
    {
        this.pomodoroBot = pomodoroBot;
    }

    @Override
    public void sendMessage(String chatId, String message)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            pomodoroBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
