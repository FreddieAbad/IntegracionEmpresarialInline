FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY pom.xml .

COPY . .

COPY target/camel-integrationinline-fabad-1.0.0-SNAPSHOT-jar-with-dependencies.jar .

CMD ["java", "-jar", "target/camel-integrationinline-fabad-1.0.0-SNAPSHOT-jar-with-dependencies.jar"]


