FROM openjdk:21-bookworm

ADD build/libs/recensioni.jar /recensioni.jar
ADD src/main/resources/application.yml /application.yml

ENTRYPOINT ["java", "-jar", "/recensioni.jar"]