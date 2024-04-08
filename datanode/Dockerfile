FROM openjdk:11-jdk
WORKDIR /app
COPY build/libs/datanode-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["sh", "-c", "java -jar datanode-0.0.1-SNAPSHOT.jar"]