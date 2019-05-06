FROM openjdk:12-jdk-alpine
VOLUME /tmp
COPY target/gs-spring-boot-0.1.0.jar app.jar
COPY application.properties .
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app.jar"]