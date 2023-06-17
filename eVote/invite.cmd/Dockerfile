FROM maven:3.8.3-openjdk-17
EXPOSE 5004
ARG JAR_FILE=/invite.cmd/target/invite.cmd-1.0.0.jar
COPY ${JAR_FILE} /invite.cmd-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/invite.cmd-1.0.0.jar"]
