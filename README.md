## OAuth2.0 server

## 인증 서버 개발
spring 프로젝트에서 더이상 인증서버지원을 하지 않기로 하여 자체 개발 하기로함   
authorization code grant type
TDD
Spring Rest Dosc를 사용하여 api 명세 문서화  
Docker 컨테이너로 실행


### spec
java 1.8  
spring boot 2.3.3  
  spring security  
  spring data jpa  
mysql  
docker


### asciidoc
src/docs/asciidoc/*.adoc 파일 변환 명령어
```
gradlew asciidoc
```

### 테스트 로그인 방법
Grant Type : Authorization Code  
Auth URL : http://localhost:8080/oauth/authorize  
Access Token URL : http://localhost:8080/oauth/token  
Client Id : cbw-client  
Client Secret : secret123  

#### login  
ID : cbw0916  
PW : pass123  
#### Admin login 
ID : admin    
PW : pass123  

### 개발일기
프로젝트 진행하면서 기록하고 싶은 것들을 일기처럼 자유롭게 정리  
[devlog.md](https://github.com/ByeongUkChoi/oauth2-server-spring-example/blob/master/DevLog.md)

### 시연 영상
[영상 보기](https://www.youtube.com/watch?v=gG1UYuitx2w)

### oauth2-server-java
java oauth2 server 라이브러리 사용  
[oauth2-server-java](https://github.com/ByeongUkChoi/oauth2-server-java)  

## OAuth2 API
### 코드 요청
#### Request
##### URL
```http request
GET /oauth/authorize?client_id={client_id}&redirect_uri={redirect_uri}&response_type=code HTTP/1.1
```
##### Parameter
|Name           |Type   |Description|Required|
|---------------|-------|-----------|--------|
|client_id      |String |client id 값|O|
|redirect_uri   |String |인증 코드가 리다이렉트 될 URI|O|
|response_type  |String |"code"로 고정|O|
#### Response
```http request
HTTP/1.1 302 Found
Content-Length: 0
Location: {redirect_uri}?code={authorize_code}
```
|Name   |Type   |Description|
|-------|-------|-----------|
|code   |String |토큰 발급에 필요한 authorization code|

### 토큰 발급
#### Request
##### URL
```http request
POST /oauth/token HTTP/1.1
Content-type: application/x-www-form-urlencoded;charset=utf-8
```
##### Parameter
|Name           |Type   |Description|Required|
|---------------|-------|-----------|--------|
|grant_type     |String |"authorization_code"로 고정|O|
|client_id      |String |client id 값|O|
|redirect_uri   |String |인증 코드가 리다이렉트 될 URI|O|
|code           |String |코드 요청으로 발급 받은 authorization code|O|
|client_secret  |String |보안 강화를 위해 추가 검증하는 코드|X|
#### Response
```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
    "token_type":"bearer",
    "access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "expires_in":43199,
    "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
    "refresh_token_expires_in":25184000
}
```
|Name                       |Type   |Description|
|---------------------------|-------|-----------|
|token_type                 |String |토큰 타입, "bearer"로 고정|
|access_token               |String |사용자 엑세스 토큰 값|
|expires_in                 |Integer|엑세스 토큰 만료 시간(초)|
|refresh_token              |String |사용자 리프레시 토큰 값|
|refresh_token_expires_in   |Integer|리프레시 토큰 만료 시간(초)|
### 토큰 갱신
#### Request
##### URL
```http request
POST /oauth/token HTTP/1.1
Content-type: application/x-www-form-urlencoded;charset=utf-8
```
##### Parameter
|Name           |Type   |Description|Required|
|---------------|-------|-----------|--------|
|grant_type     |String |"refresh_token"으로 고정|O|
|client_id      |String |client id 값|O|
|refresh_token  |String |토큰 발급 시 받은 refresh_token|O|
|client_secret  |String |보안 강화를 위해 추가 검증하는 코드|X|
#### Response
```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8
{
    "token_type":"bearer",
    "access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "expires_in":43199,
    "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
    "refresh_token_expires_in":25184000
}
```
|Name                       |Type   |Description|
|---------------------------|-------|-----------|
|token_type                 |String |토큰 타입, "bearer"로 고정|
|access_token               |String |사용자 엑세스 토큰 값|
|expires_in                 |Integer|엑세스 토큰 만료 시간(초)|
|refresh_token              |String |사용자 리프레시 토큰 값|
|refresh_token_expires_in   |Integer|리프레시 토큰 만료 시간(초)|
