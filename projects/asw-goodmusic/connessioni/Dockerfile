FROM openjdk:21-bookworm

ADD build/libs/connessioni.jar /connessioni.jar
ADD src/main/resources/application.yml /application.yml

ENTRYPOINT ["java", "-jar", "/connessioni.jar"]