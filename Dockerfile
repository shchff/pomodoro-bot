FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/pomodoro-1.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
