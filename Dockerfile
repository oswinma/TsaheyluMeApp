FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD build/libs/TsahayluMeApp-1.0-SNAPSHOT.jar app.jar
#EXPOSE 8080
#EXPOSE 5005
#ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java","-jar" ,"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","/app.jar"]
#ENTRYPOINT ["java","-jar" ,"-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-Dspring.profiles.active=sit","/app.jar"]
