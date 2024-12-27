package com.shchff.pomodoro.service;

public interface PomodoroTimer
{
    void start();
    void stop();
    void startWorkSession();
    void startBreakSession();
    void startLongBreakSession();
}
