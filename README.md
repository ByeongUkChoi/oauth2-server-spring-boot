# auth-server
OAuth2 + jwt => auth server 

## OAuth2
## 인증 서버 개발
spring 프로젝트에서 더이상 인증서버지원을 하지 않기로 하여 자체 개발 하기로함   
authorization code grant type
jwt토큰 사용  
TDD
Spring Rest Dosc를 사용하여 api 명세 문서화  


### spec
java 1.8  
spring boot 2.3  
  spring data jpa  


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

### 개발노트
프로젝트 진행하면서 기록하고 싶은 것들을 일기처럼 자유롭게 정리  
[devlog.md](https://github.com/ChoiBU/OAuth2-Server/blob/master/DevLog.md)

### oauth2-server-java
java oauth2 server 라이브러리  
[oauth2-server-java](https://github.com/ChoiBU/oauth2-server-java)  
