FROM eclipse-temurin:17-jdk-alpine as builder
RUN apk update && apk add maven
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml -T 3 package -am -Dmaven.test.skip=true

FROM eclipse-temurin:17-jdk-alpine as app
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]