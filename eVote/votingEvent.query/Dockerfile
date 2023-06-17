FROM maven:3.8.3-openjdk-17
EXPOSE 5003
ARG JAR_FILE=votingEvent.query/target/votingEvent.query-1.0.0.jar
ADD ${JAR_FILE} votingEvent.query-1.0.0.jar
ENTRYPOINT ["java","-jar","/votingEvent.query-1.0.0.jar"]