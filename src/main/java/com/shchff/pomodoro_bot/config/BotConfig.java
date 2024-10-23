package com.shchff.pomodoro_bot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BotConfig
{
    @Value("${bot.username}")
    String username;
    @Value("${bot.token}")
    String token;
}
