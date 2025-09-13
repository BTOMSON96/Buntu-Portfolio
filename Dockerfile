
# ---------------------------
# Stage 1: Build the WAR
# ---------------------------
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy Maven configuration first (for caching dependencies)
COPY pom.xml .

# Copy source code
COPY src ./src

# Build the WAR
RUN mvn clean package

# ---------------------------
# Stage 2: Deploy WAR to Tomcat
# ---------------------------
FROM tomcat:10.1-jdk17

# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR from build stage to Tomcat
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]