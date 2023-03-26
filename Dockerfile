# Используем базовый образ Maven
FROM maven:3.6.3-jdk-8-openj9 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package


FROM openjdk:8-jdk-alpine


WORKDIR /app
COPY --from=build /app/target/my-app.jar /app
EXPOSE 8080

CMD ["java", "-jar", "my-app.jar"]