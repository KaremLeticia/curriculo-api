FROM maven:3.9.5 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17
COPY --from=build /app/target/curriculo_api-0.0.1-SNAPSHOT.jar /myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/myapp.jar"]