package com.shchff.pomodoro.service;

import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TimerServiceImpl implements TimerService
{
    private static final int DEFAULT_WORK_TIME = 25;
    private static final int DEFAULT_BREAK_TIME = 5;
    private static final int DEFAULT_LONG_BREAK_TIME = 15;
    private final Map<String, PomodoroTimer> timers = new HashMap<>();

    @Override
    public TimerResult startPomodoro(String chatId)
    {
        if (timers.containsKey(chatId))
        {
            return TimerResult.FAILURE;
        }

        PomodoroTimer timer = new PomodoroTimerImpl(DEFAULT_WORK_TIME, DEFAULT_BREAK_TIME, DEFAULT_LONG_BREAK_TIME);
        timers.put(chatId, timer);
        return TimerResult.SUCCESS;
    }

    @Override
    public TimerResult stopPomodoro(String chatId)
    {
        PomodoroTimer timer = timers.remove(chatId);
        if (timer != null)
        {
            timer.stop();
            return TimerResult.SUCCESS;
        }
        return TimerResult.FAILURE;
    }
}
