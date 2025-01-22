package com.shchff.pomodoro.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface SendBotMessageService
{
    void sendMessage(String chatId, String message);
    void sendMessageWithReplyKeyboard(String chatId, String message, ReplyKeyboard replyKeyboard);
}
