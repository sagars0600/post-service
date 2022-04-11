FROM openjdk:17
ADD target/Docker-Post.jar Docker-Post.jar
EXPOSE 3010
ENTRYPOINT ["java","-jar","Docker-Post.jar"]