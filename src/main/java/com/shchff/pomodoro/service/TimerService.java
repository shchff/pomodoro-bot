package com.shchff.pomodoro.service;

import com.shchff.pomodoro.command.AskForBreakTimeCommand;
import com.shchff.pomodoro.command.BreakCommand;
import com.shchff.pomodoro.command.WorkCommand;
import com.shchff.pomodoro.entity.UserPreferences;
import com.shchff.pomodoro.repository.UserPreferencesRepository;
import com.shchff.pomodoro.service.timer.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TimerService implements TimerObserver
{
    private static final int DEFAULT_WORK_TIME = 25;
    private static final int DEFAULT_BREAK_TIME = 5;
    private final Map<String, PomodoroTimer> timers = new HashMap<>();

    private final WorkCommand workMessageCommand;
    private final BreakCommand breakMessageCommand;
    private final AskForBreakTimeCommand askForBreakTimeCommand;
    private final UserPreferencesRepository userPreferencesRepository;

    private Long getUserIdByChatId(String chatId)
    {
        return userPreferencesRepository.findByChatId(chatId)
                .map(UserPreferences::getUserId)
                .orElse(null);
    }

    public TimerResult startPomodoro(String chatId)
    {
        if (timers.containsKey(chatId))
        {
            return TimerResult.FAILURE;
        }

        PomodoroTimer timer = new PomodoroTimerImpl(DEFAULT_WORK_TIME, DEFAULT_BREAK_TIME, this, chatId);
        timer.start();
        timers.put(chatId, timer);
        return TimerResult.SUCCESS;
    }

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

    public TimerState getCurrentState(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);
        if (timer == null)
        {
            return TimerState.OFF;
        }

        return timer.getState();
    }

    public int getSessionCount(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);

        if (timer == null)
        {
            return -1;
        }

        return timer.getPomodoroCount();
    }

    public void setBreakTime(String chatId, int breakTime)
    {
        PomodoroTimer timer = timers.get(chatId);
        timer.setBreakTime(breakTime);
        timer.startBreakSession();
    }

    public int getBreakTime(String chatId)
    {
        PomodoroTimer timer = timers.get(chatId);
        return timer != null ? timer.getBreakTime() : 0;
    }

    @Override
    public void acceptStateChange(String chatId, TimerState state)
    {
        Long userId = getUserIdByChatId(chatId);

        if (state == TimerState.WORK)
        {
            workMessageCommand.execute(chatId, userId);
        }
        else if (state == TimerState.BREAK)
        {
            breakMessageCommand.execute(chatId, userId);
        }
    }

    @Override
    public void askForChangingBreakTime(String chatId)
    {
        Long userId = getUserIdByChatId(chatId);
        if (userId == null) userId = 0L;
        askForBreakTimeCommand.execute(chatId, userId);
    }
}
