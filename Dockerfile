FROM eclipse-temurin:21-jdk-alpine as builder
RUN apk update && apk add maven
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml -T 3 package -am -Dmaven.test.skip=true

FROM eclipse-temurin:21-jdk-alpine as app
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/app/*.jar"]

FROM nginx AS nginx
COPY config/nginx.conf /etc/nginx/nginx.conf
COPY --from=frontend /usr/src/app/dist/frontend/browser /usr/share/nginx/html