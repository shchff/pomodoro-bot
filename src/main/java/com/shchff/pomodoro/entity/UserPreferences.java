package com.shchff.pomodoro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_preferences")
public class UserPreferences
{
    @Id
    private Long userId;

    private String locale;

    private String chatId;
}
