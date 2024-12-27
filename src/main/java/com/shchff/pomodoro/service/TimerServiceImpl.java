package com.shchff.pomodoro.service;

import java.util.HashMap;
import java.util.Map;

public class TimerServiceImpl implements TimerService
{
    private static final int DEFAULT_WORK_TIME = 25;
    private static final int DEFAULT_BREAK_TIME = 5;
    private static final int DEFAULT_LONG_BREAK_TIME = 15;
    private final Map<String, PomodoroTimer> timers = new HashMap<>();

    @Override
    public void startPomodoro(String chatId)
    {
        if (!timers.containsKey(chatId))
        {
            // TODO: отправить сообщение, что всё работает
        }

        PomodoroTimer timer = new PomodoroTimerImpl(DEFAULT_WORK_TIME, DEFAULT_BREAK_TIME, DEFAULT_LONG_BREAK_TIME);
        timers.put(chatId, timer);
        // TODO: отправить сообщение, что таймер запущен запущен
    }

    @Override
    public void stopPomodoro(String chatId)
    {
        PomodoroTimer timer = timers.remove(chatId);
        if (timer != null)
        {
            timer.stop();
            // TODO: ВСЁ
        }
        else
        {
            // TODO: нет запущенного таймера
        }
    }
}
