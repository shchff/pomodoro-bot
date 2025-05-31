# 🍅 Pomodoro Bot

<div align="center">
    <img width="500" src="assets/pomodoro-avatar.jpg" alt="pomodoro image"/>
</div>

[Link to the bot](https://telegram.me/pomodoro_concentration_bot)

---

## 📕 Description

🍅 **The Pomodoro Method** is a time management technique developed by Francesco Cirillo in the late 1980s.

📚 **Basic principles:**
- Divide your work into short 25-minute intervals (these are called _pomodoros_), followed by short breaks of 5 minutes each.
- After four such _pomodoros_, take a longer break of 5 to 25 minutes.

🎯 The method’s goal is to help you stay focused, avoid burnout, and boost your productivity.

---

## ⚒️ Stack

- 🌱 **Spring Boot** — Java framework for developing applications.
- 🐋 **Docker & docker-compose** — to containerize and run the application.
- 🐘 **PostgreSQL** — as the relational database.
- ✈️ **Telegram Bot API** — platform for bot communication.

---

## 📥 Installation Guide

Want to run your own Pomodoro Bot? Follow these simple steps:

### 1️⃣ Register Your Bot with BotFather

1. Open [BotFather](https://t.me/BotFather) in Telegram.
2. Send the command `/newbot` and follow the instructions.
3. You will get a **bot token** like this: 7597655202:AAGjG1sZWsxst4RfNyZZtl_ddCwTT3WVOlg
4. Save your bot username (e.g., `pomodoro_concentration_bot`) and the token.

---

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/shchff/pomodoro-bot.git
cd pomodoro-bot
```

---
### 3️⃣ Create & Fill `src/main/resources/application.properties`
```properties
# Telegram Bot
bot.username=your_bot_username
bot.token=your_bot_token

# Application
spring.application.name=Pomodoro Bot

# Internationalization
spring.messages.basename=i18n/messages
spring.messages.encoding=UTF-8

# Database
spring.datasource.url=jdbc:postgresql://postgres:5432/pomodoro_db
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Logging
logging.level.root=INFO
```
---
### 4️⃣ Build the JAR File
```bash
mvn clean package
```
---
### 5️⃣ Create a `docker-compose.yml` File
```yaml
version: "3.9"

services:
  postgres:
    image: postgres:16
    container_name: pomodoro-postgres
    restart: always
    environment:
      POSTGRES_DB: your_pomodoro_db
      POSTGRES_USER: your_postgres_username
      POSTGRES_PASSWORD: your_postgres_password
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  pomodoro-bot:
    build: .
    container_name: pomodoro-bot
    depends_on:
      - postgres
    ports:
      - "8080:8080"
    restart: always
    environment:
      SPRING_PROFILES_ACTIVE: prod

volumes:
  postgres_data:
 ```
---
### 6️⃣ Start Everything 🚀
```bash
docker compose up -d --build
````
To check if the containers are running:

```bash
docker ps
```

### ⚙️ Useful Docker Commands
View the bot logs:

```bash
docker logs -f pomodoro-bot
```
Restart containers:

```bash
docker compose restart
```

Stop and remove all containers:
```bash
docker compose down
```

Press `/start` and enjoy your Pomodoro sessions! 🍅

## ❓ Usage
Here are some available bot commands:

`/start_pomodoro` — start a Pomodoro session.

`/stop_pomodoro` — stop the timer.

`/help` — get help and information.

All commands are intuitive and user-friendly! 👍