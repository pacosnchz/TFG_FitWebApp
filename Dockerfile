# ── ETAPA 1: Compilar el proyecto con Maven ──
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar pom.xml y descargar dependencias (cacheado si no cambia el pom)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copiar el resto del código y compilar
COPY src ./src
RUN mvn clean package -DskipTests -q

# ── ETAPA 2: Imagen final ligera solo con el JAR ──
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
