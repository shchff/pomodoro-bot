package com.shchff.pomodoro.service.timer;

public interface TimerService
{
    TimerResult startPomodoro(String chatId);
    TimerResult stopPomodoro(String chatId);
    TimerState getCurrentState(String chatId);
    int getSessionCount(String chatId);
}
