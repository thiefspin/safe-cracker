FROM eclipse-temurin:17-jre-alpine
MAINTAINER thiefspin
COPY build/libs/brute-cracker-1.0-SNAPSHOT-all.jar brute-cracker-1.0.0.jar
ENTRYPOINT ["java","-jar","brute-cracker-1.0.0.jar"]