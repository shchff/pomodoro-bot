package com.shchff.pomodoro.service;

import com.shchff.pomodoro.service.timer.*;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimerServiceImpl implements TimerService, TimerObserver
{
    private static final int DEFAULT_WORK_TIME = 25;
    private static final int DEFAULT_BREAK_TIME = 5;
    private final Map<String, PomodoroTimer> timers = new HashMap<>();
    private final SendBotMessageService sendBotMessageService;

    private static final String WORK_MESSAGE = "Перерыв закончился, приступай к работе!";
    private static final String BREAK_MESSAGE = "Перерыв!\nОтдыхай %s минут";
    private static final String CHANGE_BREAK_TIME_MESSAGE = "\uD83D\uDD54 Выбери время следующего перерыва (в минутах):";

    private String buildBreakMessage(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);
        int breakTime = timer.getBreakTime();
        return String.format(BREAK_MESSAGE, breakTime);
    }

    public TimerServiceImpl(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public TimerResult startPomodoro(String chatId)
    {
        if (timers.containsKey(chatId))
        {
            return TimerResult.FAILURE;
        }

        PomodoroTimer timer = new PomodoroTimerImpl(DEFAULT_WORK_TIME, DEFAULT_BREAK_TIME, this, chatId);
        timer.start();
        timers.put(chatId, timer);
        return TimerResult.SUCCESS;
    }

    @Override
    public TimerResult stopPomodoro(String chatId)
    {
        PomodoroTimer timer = timers.remove(chatId);
        if (timer == null)
        {
            return TimerResult.FAILURE;
        }

        timer.stop();
        return TimerResult.SUCCESS;
    }

    @Override
    public TimerState getCurrentState(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);
        if (timer == null)
        {
            return TimerState.OFF;
        }

        return timer.getState();
    }

    @Override
    public int getSessionCount(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);

        if (timer == null)
        {
            return -1;
        }

        return timer.getPomodoroCount();
    }

    @Override
    public void setBreakTime(String chatId, int breakTime)
    {
        PomodoroTimer timer = timers.get(chatId);
        timer.setBreakTime(breakTime);
        timer.startBreakSession();
    }

    @Override
    public void acceptStateChange(String chatId, TimerState state)
    {
        if (state == TimerState.WORK)
        {
            sendBotMessageService.sendMessage(chatId, WORK_MESSAGE);
        }
        else if (state == TimerState.BREAK)
        {
            sendBotMessageService.sendMessage(chatId, buildBreakMessage(chatId));
        }
    }

    @Override
    public void askForChangingBreakTime(String chatId)
    {
        sendBotMessageService.sendMessageWithReplyKeyboard(chatId, CHANGE_BREAK_TIME_MESSAGE, getInlineKeyBoardMessage());
    }

    public static InlineKeyboardMarkup getInlineKeyBoardMessage()
    {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton fiveMinutesButton = new InlineKeyboardButton();
        fiveMinutesButton.setText("5");
        fiveMinutesButton.setCallbackData("break_time: 5");

        InlineKeyboardButton tenMinutesButton = new InlineKeyboardButton();
        tenMinutesButton.setText("10");
        tenMinutesButton.setCallbackData("break_time: 10");

        InlineKeyboardButton fifteenMinutesButton = new InlineKeyboardButton();
        fifteenMinutesButton.setText("15");
        fifteenMinutesButton.setCallbackData("break_time: 15");

        InlineKeyboardButton twentyMinutesButton = new InlineKeyboardButton();
        twentyMinutesButton.setText("20");
        twentyMinutesButton.setCallbackData("break_time: 20");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        keyboardButtonsRow1.add(fiveMinutesButton);
        keyboardButtonsRow1.add(tenMinutesButton);
        keyboardButtonsRow2.add(fifteenMinutesButton);
        keyboardButtonsRow2.add(twentyMinutesButton);

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        keyboard.add(keyboardButtonsRow1);
        keyboard.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
