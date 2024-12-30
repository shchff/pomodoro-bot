package com.shchff.pomodoro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PomodoroBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomodoroBotApplication.class, args);
	}

}
