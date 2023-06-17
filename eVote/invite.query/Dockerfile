FROM maven:3.8.3-openjdk-17
EXPOSE 5005
ARG JAR_FILE=/invite.query/target/invite.query-1.0.0.jar
COPY ${JAR_FILE} /invite.query-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/invite.query-1.0.0.jar"]