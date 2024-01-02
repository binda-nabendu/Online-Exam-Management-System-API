FROM maven:3.8.4-openjdk-8-slim
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn clean install -DskipTests
ENTRYPOINT ["java", "-jar", "./target/Exam-management-system-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080