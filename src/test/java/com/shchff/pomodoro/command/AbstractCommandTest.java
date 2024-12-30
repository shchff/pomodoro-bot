package com.shchff.pomodoro.command;

import com.shchff.pomodoro.bot.PomodoroTelegramBot;
import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.SendBotMessageServiceImpl;
import com.shchff.pomodoro.service.timer.TimerService;
import com.shchff.pomodoro.service.timer.TimerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest
{
    protected PomodoroTelegramBot pomodoroBot = Mockito.mock(PomodoroTelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(pomodoroBot);
    protected TimerService timerService = new TimerServiceImpl(sendBotMessageService);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException
    {
        //given
        Long chatId = 1234567824356L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.setParseMode("Markdown");

        //when
        getCommand().execute(update);

        //then
        Mockito.verify(pomodoroBot).execute(sendMessage);
    }
}
