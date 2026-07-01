# Estructura de compilación (Build Stage)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar el pom y descargar dependencias (para aprovechar la caché de Docker)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar el JAR omitiendo los tests para acelerar
COPY src ./src
RUN mvn clean package -DskipTests

# Estructura de ejecución (Run Stage)
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copiar el archivo JAR generado desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto por el que escuchará la aplicación
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
