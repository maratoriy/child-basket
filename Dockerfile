FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/child-basket-*.jar child-basket.jar
ENTRYPOINT ["java", "-jar", "/child-basket.jar"]
