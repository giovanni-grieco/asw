FROM openjdk:21-bookworm

ADD build/libs/api-gateway.jar /api-gateway.jar
ADD src/main/resources/application.yml /application.yml

ENTRYPOINT ["java", "-jar", "/api-gateway.jar"]