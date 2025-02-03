FROM openjdk:17-jdk-slim-buster
LABEL authors="shchff"
WORKDIR /bot
COPY /target/pomodoro-0.5.0-SNAPSHOT.jar /bot/pomodoro.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "pomodoro.jar"]