#!/bin/bash

# Define as variáveis de ambiente
export APPLICATION_PORT=8080
export HUBSPOT_CLIENT_ID="coloque_sua_client_id_aqui"
export HUBSPOT_CLIENT_SECRET="coloque_sua_client_secret_aqui"

# Opcional: compila o projeto
./mvnw clean package

# Executa o JAR gerado
java -jar target/hubspot-integration-0.0.1-SNAPSHOT.jar

