FROM gradle:8.4-jdk21 AS build
WORKDIR /app
COPY build.gradle settings.gradle /app/
RUN gradle build --no-daemon || return 0
COPY . /app
RUN gradle build --no-daemon
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
