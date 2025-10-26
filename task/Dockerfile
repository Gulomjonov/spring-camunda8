FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim AS runner

WORKDIR /app

COPY --from=builder /app/target/task-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
