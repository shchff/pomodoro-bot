FROM meddream/jdk17
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=pomodoro_concentration_bot
ENV BOT_TOKEN=7597655202:AAGjG1sZWsxst4RfNyZZtl_ddCwTT3WVOlg
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar", "/app.jar"]