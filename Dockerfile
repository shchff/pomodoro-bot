FROM openjdk:17-jdk-slim-buster
LABEL authors="shchff"
WORKDIR /bot
COPY /target/pomodoro-1.0.0.jar /bot/pomodoro.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "pomodoro.jar"]