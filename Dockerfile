FROM openjdk:21-slim

WORKDIR /app

COPY target/eSanar-0.0.1-SNAPSHOT.jar /app/eSanar.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar", "eSanar.jar"]