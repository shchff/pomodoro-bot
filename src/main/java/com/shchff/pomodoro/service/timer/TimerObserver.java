package com.shchff.pomodoro.service.timer;

public interface TimerObserver
{
    void acceptStateChange(String chatId, TimerState state);
    void askForChangingBreakTime(String chatId);
}
