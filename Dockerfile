FROM openjdk:17-slim

WORKDIR /app

COPY target/eSanar-0.0.1-SNAPSHOT.jar /app/eSanar.jar

ENTRYPOINT ["java","-jar", "eSanar.jar"]