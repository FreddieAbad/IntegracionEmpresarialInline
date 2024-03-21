FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY pom.xml .
COPY src ./src
COPY start.sh .

RUN mvn clean install
RUN mvn clean package
COPY target/camel-integrationinline-fabad-1.0.0-SNAPSHOT.jar ./camel-integrationinline-fabad-1.0.0-SNAPSHOT.jar

CMD ["java", "-jar", "camel-integrationinline-fabad-1.0.0-SNAPSHOT.jar"]


