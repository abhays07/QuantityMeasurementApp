# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Memory optimizations for your 1GB EC2
ENV JAVA_OPTS="-Xms64m -Xmx160m -XX:+UseContainerSupport"

EXPOSE 8082 
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]