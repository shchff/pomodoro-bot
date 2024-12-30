package com.shchff.pomodoro.service.timer;

import com.shchff.pomodoro.service.SendBotMessageService;

public interface TimerService
{
    TimerResult startPomodoro(String chatId);
    TimerResult stopPomodoro(String chatId);
    TimerState getCurrentState(String chatId);
    int getSessionCount(String chatId);
}
