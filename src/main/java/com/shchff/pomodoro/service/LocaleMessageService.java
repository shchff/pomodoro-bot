package com.shchff.pomodoro.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocaleMessageService
{
    private final MessageSource messageSource;
    private final UserPreferencesService userPreferencesService;

    public String getMessage(String code, Long userId)
    {
        Locale userLocale = userPreferencesService.getUserLocale(userId);
        return messageSource.getMessage(code, null, userLocale);
    }
}
