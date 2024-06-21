#FROM maven:3.9.7-sapmachine-22 AS build
#WORKDIR /app
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#COPY src ./src
#RUN mvn package -DskipTests
#RUN echo "done"

FROM openjdk:21-slim
WORKDIR /app
LABEL maintainer="rizaldyimanputra@gmail.com"
COPY ./target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]