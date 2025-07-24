# Etapa 1: Compilación
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:17-jdk-slim
COPY --from=build target/microservicio-razas-0.0.1-SNAPSHOT.jar microservicio_razas.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","microservicio_razas.jar"]