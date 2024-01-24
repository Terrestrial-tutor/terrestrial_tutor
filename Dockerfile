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

FROM node:18.15.0-alpine AS frontend
WORKDIR /usr/src/app
COPY ./frontend/package.json ./frontend/package-lock.json ./
RUN npm install
COPY ./frontend .
EXPOSE 4200
RUN npm run build
ENTRYPOINT ["npm", "start"]