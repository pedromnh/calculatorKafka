FROM amazoncorretto:21
WORKDIR /exercise
COPY build/libs/calculator-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]