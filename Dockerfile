FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/backend-p1-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
#LABEL authors="cnith"
#
#ENTRYPOINT ["top", "-b"]