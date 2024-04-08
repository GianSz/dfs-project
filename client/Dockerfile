FROM openjdk:11-jdk
WORKDIR /app
COPY build/libs/client-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["sh", "-c", "java -jar client-0.0.1-SNAPSHOT.jar"]