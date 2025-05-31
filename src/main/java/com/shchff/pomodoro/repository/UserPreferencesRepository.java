package com.shchff.pomodoro.repository;

import com.shchff.pomodoro.entity.UserPreferences;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long>
{
    Optional<UserPreferences> findByChatId(String chatId);
}
