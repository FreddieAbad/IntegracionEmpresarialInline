#!/bin/bash

# Ruta al archivo JAR
JAR_FILE="/app/target/camel-integrationinline-fabad-1.0.0-SNAPSHOT.jar"

# Comando para ejecutar el archivo JAR
java -jar "$JAR_FILE" org.apache.camel.learn.MainApp
