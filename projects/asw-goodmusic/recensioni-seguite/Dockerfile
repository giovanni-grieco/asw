FROM openjdk:21-bookworm

ADD build/libs/recensioni-seguite.jar /recensioni-seguite.jar
ADD src/main/resources/application.yml /application.yml

ENTRYPOINT ["java", "-jar", "/recensioni-seguite.jar"]