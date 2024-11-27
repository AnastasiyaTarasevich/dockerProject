# Используем образ с OpenJDK 17
FROM openjdk:17

# Задаем переменные окружения
ENV MONGO_DB_USERNAME=admin \
    MONGO_DB_PW=password

# Создаем директорию для приложения
WORKDIR /home/app

# Копируем JAR-файл (результат сборки приложения) в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Указываем команду для запуска JAR-файла
CMD ["java", "-jar", "app.jar"]
