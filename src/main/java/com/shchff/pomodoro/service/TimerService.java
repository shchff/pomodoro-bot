package com.shchff.pomodoro.service;


import com.shchff.pomodoro.service.timer.TimerResult;
import com.shchff.pomodoro.service.timer.TimerState;

public interface TimerService
{
    TimerResult startPomodoro(String chatId);
    TimerResult stopPomodoro(String chatId);
    TimerState getCurrentState(String chatId);
    int getSessionCount(String chatId);
    void setBreakTime(String chatId, int breakTime);
}
