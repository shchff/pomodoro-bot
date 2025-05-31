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

    public String getMessage(String code, Locale locale, Object... args)
    {
        return messageSource.getMessage(code, args, locale);
    }
}
