## 개발 노트
### 개발하면서 기록하고 싶은 것을 남김

#### 2020. 05. 29
이왕 시작하는거 가장 최신 버전과 모듈을 사용하기로 하였다.

#### 2020. 05. 30
spring-security-oauth2 은 더이상 사용되지 않는다고 한다.
spring-cloud-starter-oauth2와 spring-cloud-starter-security로 대체하였다.

#### 2020. 05. 31
카카오톡으로 로그인하기 기능을 참고하여 명세는 카카오 인증서버를 참고 할 계획이다.  
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

#### 2020. 06. 05
asciidoc 파일에서 gradle 변수를 가져오지 못하는 문제 해결하여  
정상적으로 문서 생성됨 (api-docs.adoc 파일의 맨 위에 변수 선언함)  
6월1일에 @RestClientTest를 사용하기로 하였으나  
@MockMvcTest를 사용하기로함  
rest docs 생성을 위해서면 간단히 컨트롤러 테스트만 하는것이 적합하다고 판단함  

#### 2020. 06. 06
로그인 페이지를 만들어야 할것 같은데 spring security를 이용해야 되는지 고민중이다.
아직도 oauth2의 동작 방식을 제대로 이해하지 못한것 같다.  
카카오 간편 로그인을 포스트맨으로 실행해서 어떻게 작동되는지 이해하도록 하자.

#### 2020. 06. 07
oauth2인증 방식을 어느정도 이해한것 같다. 까먹지 않도록 블로그에 잘 정리하자.  
[블로그 정리 글](https://unhosted.tistory.com/37)

#### 2020. 06. 10
패스워드 검증 안하고 발급만 하는 것은 거의 마무리가 되었다.  
다음 프로젝트도 생각해놔야 끊기지 않고 바로 이어갈 수 있을것 같다.  
검증 없이 값 가져오는 것은 끝났다.  

#### 2020. 06. 11
카카오 간편 로그인 api 명세 보면서 필수값 확인중 특이사항 발견  
토큰 받기와 토큰 갱신하기가 URL은 같으나 공통 필수 파라메터 두개를 제외하고 나머지 다름    
이 경우 컨트롤러에서 어떻게 나눌수 있는지  
컨트롤러를 두개로 나누는 것이 가능한지 확인 필요  
다른 기능이기때문에 컨트롤러를 파라미터 값으로 두개로 분리하는게 제일 좋을것 같음  
추가로 @RequestParam 대신 객체로 받는 방법도 생각해보고 적용하기  

#### 2020. 06. 15
jwt를 사용하였다.  
모듈을 사용하기 위해 build.gradle에 추가하고 사용하였다.  
refresh token도 jwt로 해야하는지 고민해봐야한다.  

#### 2020. 06. 16
로그인 성공시 authenticate code를 반환하고 그 값을 다시 인증서버로 보내 토큰을 발급한다  
이때 code를 db에 저장해서 검증할지?  그렇게 한다면 로그인 성공시 db에 저장하고 로그인 서버와 인증서버가 같은 db를 봐야한다  
이 문제는 jwt를 사용하면 해결될것 같다. 하지만 카카오는 code와 access_token, refresh_token을 jwt를 사용하지 않는다.  
code는 db, token jwt를 사용하자  

h2로 테스트 데이터베이스 연결 완료  
최신 h2에서는 자동으로 데이터베이스 생성이 불가능하여 application.properties에 설정해줘야한다  

코드를 도메인 단위로 패키지를 분리하였다. 패키지의 크기가 커지면 레이어 단위로 하위 패키지를 분리할 예정이다.  

#### 2020. 06. 18
패스워드 검사는 완료하였다.  
패스워드를 평문이 아닌 암호화해서 저장해야한다.  
적합한 알고리즘을 적용해서 바꾸자. (BCRYPT, ARGON2I, ARGON2ID 등)  

client_id, client_secret, authorize_code 값을 저장할 테이블이 필요하다.  

#### 2020. 06. 26
client_id, client_secret db 검증을 해야한다.  
테이블 구성을 다시 해서 스키마를 한눈에 볼수 있도록 정리해보자.  

#### 2020. 06. 27
깃허브에서 OAuth2 server 검색 시 가장 많은 별을 받은 오픈소스를 참조 하여 database 스키마를 구성하고,  
구현 로직을 참조할 예정이다.  
OAuth2 server (PHP) 오픈 소스  
[thephpleague/oauth2-server document](https://oauth2.thephpleague.com/)  
[bshaffer/oauth2-server-php database schema](http://bshaffer.github.io/oauth2-server-php-docs/cookbook/)  

#### 2020. 06. 30
OAuth와 로그인을 다른 패키지로 두어야하는지 고민을 하였다.  
카카오 간편로그인의 경우 oauth와 로그인이 다른 주소를 갖고있기 때문에 나누었다.  
하나로 합쳐야 할지는 고민을 해봐야 겠다.  

@Entity의 멤버 변수를 카멜케이스로 하면 데이터베이스에는 스네이크케이스로 변경된다.  

