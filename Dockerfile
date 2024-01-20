FROM eclipse-temurin:21-jdk-alpine as builder
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . /app/.
ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]