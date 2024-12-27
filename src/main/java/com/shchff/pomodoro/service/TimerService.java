package com.shchff.pomodoro.service;

public interface TimerService
{
    TimerResult startPomodoro(String chatId);
    TimerResult stopPomodoro(String chatId);
}
