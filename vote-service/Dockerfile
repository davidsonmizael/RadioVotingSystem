FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM java:8
EXPOSE 8083
COPY --from=build /home/app/target/vote-service-1.0.0.jar /usr/local/lib/vote-service.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/vote-service.jar"]