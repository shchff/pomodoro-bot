package com.shchff.pomodoro.service.timer;

public interface PomodoroTimer
{
    void start();
    void stop();
    void startWorkSession();
    void startBreakSession();
    void startLongBreakSession();
    void setLongBreakTime(int minutes);
    int getPomodoroCount();
    TimerState getState();
}
