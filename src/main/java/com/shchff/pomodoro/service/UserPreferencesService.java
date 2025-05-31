package com.shchff.pomodoro.service;

import com.shchff.pomodoro.entity.UserPreferences;
import com.shchff.pomodoro.repository.UserPreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPreferencesService
{
    private final UserPreferencesRepository userPreferencesRepository;

    public Locale getUserLocale(Long userId)
    {
        Optional<UserPreferences> preferences = userPreferencesRepository.findById(userId);
        return preferences.map(userPreferences -> new Locale(userPreferences.getLocale()))
                .orElseGet(Locale::getDefault);
    }

    public void saveUserLocale(Long userId, String locale)
    {
        Optional<UserPreferences> optionalPreferences = userPreferencesRepository.findById(userId);
        UserPreferences preferences = optionalPreferences.orElseGet(() -> {
            UserPreferences newPref = new UserPreferences();
            newPref.setUserId(userId);
            newPref.setLocale(locale);
            return newPref;
        });

        preferences.setLocale(locale);
        userPreferencesRepository.save(preferences);
    }

    public void registerUserIfNotExists(Long userId, String chatId)
    {
        Optional<UserPreferences> optionalPreferences = userPreferencesRepository.findById(userId);
        if (optionalPreferences.isEmpty())
        {
            UserPreferences preferences = new UserPreferences();
            preferences.setUserId(userId);
            preferences.setChatId(chatId);
            preferences.setLocale(Locale.getDefault().getLanguage()); // по умолчанию
            userPreferencesRepository.save(preferences);
        }
        else
        {
            UserPreferences preferences = optionalPreferences.get();
            if (!chatId.equals(preferences.getChatId()))
            {
                preferences.setChatId(chatId);
                userPreferencesRepository.save(preferences);
            }
        }
    }

    public Long getUserIdByChatId(String chatId)
    {
        return userPreferencesRepository.findByChatId(chatId)
                .map(UserPreferences::getUserId)
                .orElse(0L);
    }
}
