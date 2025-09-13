FROM tomcat:10.1-jdk17

# Remove default webapps if you want a clean deploy
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your WAR to Tomcat
COPY target/Buntu-Portfolio.war /usr/local/tomcat/webapps/

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
