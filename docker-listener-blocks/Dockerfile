FROM maven:3.6.3-jdk-11 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

FROM openjdk:11
COPY --from=build /app/target/docker-listener-blocks-1.0-SNAPSHOT.jar /app/docker-listener-blocks-1.0-SNAPSHOT.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/docker-listener-blocks-1.0-SNAPSHOT.jar"]
