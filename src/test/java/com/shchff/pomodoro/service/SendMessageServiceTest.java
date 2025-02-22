package com.shchff.pomodoro.service;

import com.shchff.pomodoro.bot.PomodoroTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.TestPropertySource;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageService")
public class SendMessageServiceTest
{
    private SendBotMessageService sendBotMessageService;
    private PomodoroTelegramBot pomodoroBot;

    @BeforeEach
    public void init()
    {
        pomodoroBot = Mockito.mock(PomodoroTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(pomodoroBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException
    {
        //given
        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);

        //when
        sendBotMessageService.sendMessage(chatId, message);

        //then
        Mockito.verify(pomodoroBot).execute(sendMessage);
    }
}
