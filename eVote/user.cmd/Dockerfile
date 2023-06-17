FROM maven:3.8.3-openjdk-17
EXPOSE 5000
ARG JAR_FILE=user.cmd/target/user.cmd-1.0.0.jar
ADD ${JAR_FILE} user.cmd-1.0.0.jar
ENTRYPOINT ["java","-jar","/user.cmd-1.0.0.jar"]

