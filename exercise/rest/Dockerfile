FROM amazoncorretto:21
WORKDIR /exercise
COPY build/libs/rest-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]