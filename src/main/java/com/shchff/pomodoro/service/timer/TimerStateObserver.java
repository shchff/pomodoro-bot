package com.shchff.pomodoro.service.timer;

public interface TimerStateObserver
{
    void acceptStateChange(String chatId, TimerState state);
}
