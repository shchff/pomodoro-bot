package com.shchff.pomodoro.service;

public interface TimerService
{
    void startPomodoro(String chatId);
    void stopPomodoro(String chatId);
}
