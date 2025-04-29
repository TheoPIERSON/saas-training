# Étape de build
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copier uniquement les fichiers nécessaires pour restaurer les dépendances rapidement
COPY pom.xml .
COPY src ./src

# Compiler le projet (skip tests pour plus de rapidité)
RUN mvn clean package -DskipTests

# Étape d'exécution
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copier le jar depuis l'étape précédente
COPY --from=build /app/target/*.jar app.jar

# Exposer le port standard de Spring Boot (8080)
EXPOSE 8080

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
