# 🍅 Pomodoro Bot

## 📕 Description

🍅 The Pomodoro Method is a time management technique developed by Francesco Cirillo in the late 1980s.

📚 Basic principles:\
1️⃣ Divide your work into short 25-minute intervals (these are called “pomodoros”), followed by short breaks of 5 minutes each. \
2️⃣ After four such “pomodoros”, take a longer break in the range of 5 to 25 minutes.

🎯 The purpose of the method is to help you concentrate, avoid burnout and increase your productivity.

## ⚒️ Stack

- 🌱 **Spring Boot** - to develop
- 🐋 **Docker** - to run
- ✈️ **Telegram** - platform

## 📥 Installation guide

If you want to play with the bot code, follow these **simple steps**:

1. Register your bot \
It's pretty easy, just follow the [BotFather](https://telegram.me/BotFather) guide.
2. Clone repo:
```shell
git clone https://github.com/shchff/pomodoro-bot.git
```
3. Navigate to the project directory
```shell
cd pomodoro-bot
```
4. Build and run Docker-Container:
```shell
docker build -t your-docker-image-name .
```
Insert **your_bot_token** and **your_bot_name** which you've registered on the 1st step:
```shell
docker run -e BOT_TOKEN=your_bot_token -e BOT_NAME=your_bot_name -p 8080:8080 your-docker-image-name
```

## ❓ Expluatation guide

The commands presented are intuitive:
- `/start_pomodoro` - to start your work
- `/stop_pomodoro` - to stop

For more information use `/help` command.

