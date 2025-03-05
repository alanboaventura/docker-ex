# Versão Java
FROM openjdk:23-jdk-slim

# Copia o arquivo JAR da aplicação para dentro do container
COPY target/docker-ex-1.0.0.jar app.jar

# Define a variável de ambiente SERVER_PORT para 9999 nas execuções via Docker
ENV SERVER_PORT=9999
EXPOSE 9999

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]