FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/demo2.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

docker build -t indrajit/demo2:latest .
docker images | grep demo2
docker run -p 8081:8080 indrajit/demo2:latest
curl http://localhost:8081
