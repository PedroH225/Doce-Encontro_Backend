FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache bash curl

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY wait-for-mysql.sh .

RUN chmod +x mvnw && ./mvnw dependency:go-offline
RUN chmod +x wait-for-mysql.sh

COPY src ./src

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["./wait-for-mysql.sh", "java", "-jar", "target/doce-encontro-0.0.1-SNAPSHOT.jar"]
