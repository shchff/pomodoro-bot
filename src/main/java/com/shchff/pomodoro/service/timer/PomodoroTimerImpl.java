package com.shchff.pomodoro.service.timer;

import lombok.Getter;
import lombok.Setter;

import java.util.Timer;
import java.util.TimerTask;

public class PomodoroTimerImpl implements PomodoroTimer
{
    private final String id;
    private final int workTime;
    @Setter
    @Getter
    private int breakTime;
    @Getter
    private int pomodoroCount;
    private Timer timer;
    @Getter
    private TimerState state;
    private final TimerObserver observer;

    public PomodoroTimerImpl(int workTime, int breakTime, TimerObserver observer, String id)
    {
        this.workTime = workTime;
        this.breakTime = breakTime;
        this.observer = observer;
        this.id = id;
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
        if (pomodoroCount > 1)
        {
            observer.acceptStateChange(id, state);
        }
        timer.schedule(new TimerTask()
            {
                @Override
                public void run()
                {
                    if (pomodoroCount < 5)
                    {
                        startBreakSession();
                    }
                    else
                    {
                        observer.askForChangingBreakTime(id);
                    }
                }
            }, (long) workTime * 60 * 1000
        );
    }

    @Override
    public void startBreakSession()
    {
        state = TimerState.BREAK;
        observer.acceptStateChange(id, state);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startWorkSession();
            }
        }, (long) breakTime * 60 * 1000);
    }

}
