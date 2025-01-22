package com.shchff.pomodoro.service.timer;

public interface PomodoroTimer
{
    void start();
    void stop();
    void startWorkSession();
    void startBreakSession();
    int getPomodoroCount();
    int getBreakTime();
    void setBreakTime(int breakTime);
    TimerState getState();
}
