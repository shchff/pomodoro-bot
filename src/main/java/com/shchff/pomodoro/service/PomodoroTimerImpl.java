package com.shchff.pomodoro.service;

import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerImpl implements PomodoroTimer
{
    private final int workTime;
    private final int breakTime;
    private int longBreakTime;
    private int pomodoroCount;
    private boolean isRunning;
    private Timer timer;

    public PomodoroTimerImpl(int workTime, int breakTime, int longBreakTime)
    {
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.longBreakTime = longBreakTime;
        this.pomodoroCount = 0;
        this.isRunning = false;
    }

    @Override
    public void start()
    {
        isRunning = true;
        timer = new Timer();
        startWorkSession();
    }

    @Override
    public void stop()
    {
        isRunning = false;
        timer.cancel();
    }

    @Override
    public void startWorkSession()
    {
        timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    pomodoroCount++;
                    if (pomodoroCount >= 4)
                    {
                        startBreakSession();
                    }
                    else
                    {
                        startLongBreakSession();
                    }
                }
            }, (long) workTime * 60 * 1000
        );
    }

    @Override
    public void startBreakSession()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startWorkSession();
            }
        }, (long) breakTime * 60 * 1000);
    }

    @Override
    public void setLongBreakTime(int minutes)
    {
        if (minutes >= 10 && minutes <= 30)
        {
            longBreakTime = minutes;
        }
    }

    @Override
    public void startLongBreakSession()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startWorkSession();
            }
        }, (long) longBreakTime * 60 * 1000);
    }
}
