## Package java application using maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

## Run Java application
FROM alpine/java:21
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT [ "java","-jar","/app/app.jar" ]
