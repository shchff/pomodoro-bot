package com.shchff.pomodoro.service.timer;

import com.shchff.pomodoro.service.SendBotMessageService;
import org.jvnet.hk2.annotations.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TimerServiceImpl implements TimerService
{
    private static final int DEFAULT_WORK_TIME = 25;
    private static final int DEFAULT_BREAK_TIME = 5;
    private final Map<String, PomodoroTimer> timers = new HashMap<>();
    private final SendBotMessageService sendBotMessageService;

    public TimerServiceImpl(SendBotMessageService sendBotMessageService)
    {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public TimerResult startPomodoro(String chatId)
    {
        if (timers.containsKey(chatId))
        {
            return TimerResult.FAILURE;
        }

        PomodoroTimer timer = new PomodoroTimerImpl(DEFAULT_WORK_TIME, DEFAULT_BREAK_TIME);
        timer.start();
        timers.put(chatId, timer);
        return TimerResult.SUCCESS;
    }

    @Override
    public TimerResult stopPomodoro(String chatId)
    {
        PomodoroTimer timer = timers.remove(chatId);
        if (timer == null)
        {
            return TimerResult.FAILURE;
        }

        timer.stop();
        return TimerResult.SUCCESS;
    }

    @Override
    public TimerState getCurrentState(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);
        if (timer == null)
        {
            return TimerState.OFF;
        }

        return timer.getState();
    }

    @Override
    public int getSessionCount(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);

        if (timer == null)
        {
            return -1;
        }

        return timer.getPomodoroCount();
    }
}
