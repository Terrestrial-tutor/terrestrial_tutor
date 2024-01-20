FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY *.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
