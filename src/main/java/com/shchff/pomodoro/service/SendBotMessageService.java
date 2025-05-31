package com.shchff.pomodoro.service;

import com.shchff.pomodoro.bot.PomodoroTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageService
{
    private final PomodoroTelegramBot pomodoroBot;

    @Autowired
    public SendBotMessageService(@Lazy PomodoroTelegramBot pomodoroBot)
    {
        this.pomodoroBot = pomodoroBot;
    }

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

    public void sendMessageWithReplyKeyboard(String chatId, String message, ReplyKeyboard replyKeyboard)
    {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        sendMessage.setReplyMarkup(replyKeyboard);

        try {
            pomodoroBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }
}
