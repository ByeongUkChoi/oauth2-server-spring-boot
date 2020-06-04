## 개발 노트
### 개발하면서 기록하고 싶은 것을 남김

#### 2020. 05. 29
이왕 시작하는거 가장 최신 버전과 모듈을 사용하기로 하였다.

#### 2020. 05. 30
spring-security-oauth2 은 더이상 사용되지 않는다고 한다.
spring-cloud-starter-oauth2와 spring-cloud-starter-security로 대체하였다.

#### 2020. 05. 31
카카오톡으로 로그인하기 기능을 참고하여 명세는 카카오 인증서버를 참고할 계획이다.  
[카카오 로그인 API](https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api)

#### 2020. 06. 01
TODO: rest api test를 위해 @RestClientTest를 사용해보자  

#### 2020. 06. 02
스프링 프로젝트에서 spring-security-oauth 더이상 지원하지 않고
spring security에서 인증 관련 부분을 진행하는데 "No Authorization Server Support"라고 한다
아래 링크 참조
[스프링 블로그 spring security oauth2 roadmap](https://spring.io/blog/2019/11/14/spring-security-oauth-2-0-roadmap-update)
[스프링 블로그 인증서버 발표](https://spring.io/blog/2020/04/15/announcing-the-spring-authorization-server)

스프링 프로젝트에서는 아래와 같은 오픈소스를 사용하라고 한다.
https://www.keycloak.org/

#### 2020. 06. 04
asciidoc파일을 html로 변환은 하였으나 
adoc파일을 불러오지 못함


