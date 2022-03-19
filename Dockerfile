#BUILD STAGE
FROM maven:3.8.4-openjdk-11-slim as build
MAINTAINER 'Adam Mendak'
RUN mvn clean install

# RUN STAGE
FROM openjdk:11-jre-slim as run
COPY --from=build rest/target/*.jar /adammendak_atm_rest.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/adammendak_atm_rest.jar"]