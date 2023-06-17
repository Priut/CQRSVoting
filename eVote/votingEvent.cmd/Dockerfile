FROM maven:3.8.3-openjdk-17
EXPOSE 5002
ARG JAR_FILE=votingEvent.cmd/target/votingEvent.cmd-1.0.0.jar
ADD ${JAR_FILE} votingEvent.cmd-1.0.0.jar
ENTRYPOINT ["java","-jar","/votingEvent.cmd-1.0.0.jar"]