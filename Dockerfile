## dockerfile test
FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

## 도커 이미지 빌드 전에 어플리케이션을 빌드 해야 한다.
## gradlew build

## 도커 이미지 빌드 방법
#docker build --build-arg JAR_FILE=build/libs/*.jar -t byeongukchoi/oauth2-server .
## 도커 이미지로 컨테이너 실행 (background)
#docker run -d --name oauth2-server-container -p 8080:8080 --link mysql-db byeongukchoi/oauth2-server
# 외부포트 5000
#docker run -d --name oauth2-server-container -p 5000:8080 --link mysql-db byeongukchoi/oauth2-server
# 도커 허브에서 이미지 받아서 컨테이너 실행
#docker run -d --name oauth2-server-container -p 80:8080 --link mysql-db cbw0916/spring-boot-oauth2-server

## 도커 MySQL 컨테이너 실행
#docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=pass123 -d mysql