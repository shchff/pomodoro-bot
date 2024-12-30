package com.shchff.pomodoro.service.timer;

import com.shchff.pomodoro.service.SendBotMessageService;
import com.shchff.pomodoro.service.SendBotMessageServiceImpl;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerImpl implements PomodoroTimer
{
    private final int workTime;
    private final int breakTime;
    private int pomodoroCount;
    private Timer timer;
    private TimerState state;

    public PomodoroTimerImpl(int workTime, int breakTime)
    {
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.pomodoroCount = 0;
    }

    @Override
    public void start()
    {
        timer = new Timer();
        startWorkSession();
    }

    @Override
    public void stop()
    {
        timer.cancel();
    }

    @Override
    public void startWorkSession()
    {
        state = TimerState.WORK;
        pomodoroCount++;
        timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    startBreakSession();
                }
            }, (long) workTime * 60 * 1000
        );
    }

    @Override
    public void startBreakSession()
    {
        state = TimerState.BREAK;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startWorkSession();
            }
        }, (long) breakTime * 60 * 1000);
    }

    @Override
    public int getPomodoroCount()
    {
        return pomodoroCount;
    }

    @Override
    public TimerState getState()
    {
        return state;
    }
}
