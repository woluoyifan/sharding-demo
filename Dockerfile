FROM openjdk:8
VOLUME /tmp
ADD ./target/springboot-docker-demo-1.0-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]