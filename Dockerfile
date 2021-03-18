FROM openjdk:14
ARG JAR_FILE=target/jwt-example-project-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","app.jar"]