FROM openjdk:alpine3.8
LABEL maintainer="jugarcia01@hotmail.com"
VOLUME /main-app
EXPOSE 8080
COPY target/*.jar /app/app.jar
#ADD target/GraphQLwithSpringBootAppHexagonalArq-0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar","/app.jar"]
