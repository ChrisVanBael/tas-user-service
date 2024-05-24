FROM maven:3-eclipse-temurin-21 as build
WORKDIR /build
COPY . .
RUN mvn clean package

FROM eclipse-temurin:21-jre-alpine as run

EXPOSE 8080

WORKDIR /app
COPY --from=build /build/target/tas-user-service-0.0.1.jar /app

ENTRYPOINT ["java","-jar","/app/tas-user-service-0.0.1.jar"]
