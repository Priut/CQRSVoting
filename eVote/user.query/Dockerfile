FROM maven:3.8.3-openjdk-17
EXPOSE 5001
ARG JAR_FILE=user.query/target/user.query-1.0.0.jar
ADD ${JAR_FILE} user.query-1.0.0.jar
ENTRYPOINT ["java","-jar","/user.query-1.0.0.jar"]