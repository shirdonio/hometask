FROM java:8-jdk-alpine
COPY ./target/ShirD-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
CMD ["java", "-jar", "ShirD-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080