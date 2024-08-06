FROM maven:latest AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app
RUN mvn package 
 
FROM openjdk:17

ARG JAR_FILE=translator.jar

WORKDIR /opt/app

COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","translator.jar"]