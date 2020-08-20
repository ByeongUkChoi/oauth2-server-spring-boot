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
[thephpleague/oauth2-server github](https://github.com/thephpleague/oauth2-server)  
[thephpleague/oauth2-server database schema](http://vpaste.net/lQmK1)  
[bshaffer/oauth2-server-php database schema](http://bshaffer.github.io/oauth2-server-php-docs/cookbook/)  

#### 2020. 06. 30
OAuth와 로그인을 다른 패키지로 두어야하는지 고민을 하였다.  
카카오 간편로그인의 경우 oauth와 로그인이 다른 주소를 갖고있기 때문에 나누었다.  
하나로 합쳐야 할지는 고민을 해봐야 겠다.  

@Entity의 멤버 변수를 카멜케이스로 하면 데이터베이스에는 스네이크케이스로 변경된다.  

#### 2020. 07. 05
authorize_code는 카카오 간편 로그인과 동일하게 86자리로 하였다.  
access_token같은 경우 jwt를 사용할 예정이기 때문에 짧게 할 생각이다.  

이제 테스트 케이스 작성을 해야 하고, 상세한 문서 작성도 필요하다.  

athorize_code, refresh_token의 만료시간에 대해서 생각 해야 한다.  
1번. db에 만료되는 시간을 저장할 것인지  
2번. 생성 시간만 저장하고 기간을 변수로 둘 것인지  
2-1번. 생성 시간과 만료시간 모두 저장할것 인지  
1번과 2-1번은 생성 시점에 만료되는 시점이 결정되고  
2번은 해당 토큰(코드)로 요청 시점에 만료되는 시점이 결정된다  

jwt는 payload에 생성시간(iat), 만료시간(exp)이 담겨있다.  
따라서 2-1번이 제일 적합해 보인다.  만료 기간은 모두 상수로 빼두어 한번에 관리하도록 하자.  
db에 저장될 컬럼명과 형식을 고민해야 한다.  

#### 2020. 07. 09
로그인 서버와 인증 서버가 다를 경우 로그인 여부 확인에 대한 고민  
인증 코드 요청 시 로그인 여부를 파악하여 로그인 페이지로 리다이렉트 시킨다.  
로그인 되고 나서 다시 인증 코드 요청을 보내는데 이곳에서 로그인 되어있는지 아는 방법을 고민해야 한다.  

[방법 1] 인증 코드 요청 시 헤더에 쿠키로 넘어온 세션아이디가 존재할 경우 로그인 서버로 보내 로그인 여부를 확인 및 정보를 가져온다.  
1. 로그인 완료 시 로그인 서버에서 세션에 저장하고 세션 아이디는 브라우저 쿠키에 저장된다. 
(이때 카카오의 경우 로그인 서버와 인증 서버의 주소가 다르나 (로그인 서버 : accounts.kakao.com, 인증 서버 : kauth.kakao.com) 쿠키의 도메인을 .kakao.com으로 한다. )  
2. 다시 인증 코드 요청시 쿠키가 인증 서버로 전달된다 (최상위 도메인을 같게 하고 쿠키의 도메인도 카카오 처럼 최상위 도메인을 사용하여 공유하도록 한다.)  
3. 인증 서버에서는 전달받은 세션아이디를 로그인 서버에 전달하여 로그인 여부를 파악하고 정보를 받아온다.  
4. 다음 단계 진행한다. (code발급하여 redirect_uri로 전달)  

[방법 2]  인증 코드 요청 시 헤더에 jwt가 넘어온 경우 검증하여 로그인 여부를 확인 및 정보를 가져온다.  
1. 로그인 완료 시 서버는 jwt토큰을 발급하여 반환한다. (jwt에는 사용자의 간단한 정보가 담겨있다.)  
2. 로그인 완료 다음 페이지에서는 서버에서 받아온 jwt를 가지고 인증 코드 요청을 보낸다.  
3. 인증 코드 요청시 jwt를 검증하여 로그인 여부를 파악하고 정보를 받아온다.  
4. 다음 단계 진행한다. (code발급하여 redirect_uri로 전달)  

 위 두가지 방법외에도 다른 방법이 있는지 파악해야 한다.  
 일단 방법1처럼 쿠키에 세션정보를 넘겨 확인하는 것으로 예상된다.  
 일단 로그인 서버에서 쿠키를 내려줄때 최상위 도메인으로 내려주면 브라우저에서 두 서버모두 요청보낼때 쿠키를 넘긴다. 
 그러나 여기서 로그인 후 로그인 서버에서 받은 쿠키를 인증서버로 넘기는데 인증 서버에서 로그인 서버로 쿠키에 담긴 세션정보를 넘겨서 확인해아 하는지?  
 
#### 2020. 07. 12
테스트 케이스를 만들자.  
@SpringBootTest는 통합 테스트이다.  
모든 Bean을 올리고 테스트하기 때문에 실제 환경과 유사하고 쉽게 테스트가 가능하고, 요청부터 응답까지 전체 테스트가 가능하다.  
하지만 테스트 시간이 오래 걸리고, 테스트의 단위가 커 디버깅에 어려움이 있다.  
그러나 일단 API 테스트를 진행하기 위해 @SpringBootTest 어노테이션을 사용하여 테스트 케이스를 작성할 것이다.  

#### 2020. 07. 14
토큰 발급 시 추상화 시키기  
grant_type에 따라 추상화를 시킨다.  
AuthorizationCode, RefreshToken, 추후 다른 타입도 적용 가능  
컨트롤러에서 grant_type에따라 추상화된 객체를 가져오고,  
grant_type에 따라 매개변수도 다르기 때문에 이 부분도 고려해야한다.  

```
switch (grantType)
 case: AuthorizationCode
   GrantTypeInterface grantType = new AuthorizationCode(client_id, redirect_uri, code, client_secret);
 case: RefreshToken
   GrantTypeInterface grantType = new RefreshToken(client_id, refresh_token, client_secret);

Token token = grantType.getToken();
```
위와 같은 방식이면 grant_type에 대하여 추상화하여 확장성 있게 작성할 수 있어 보인다.  
오픈 소스에도 grant_type에 대하여 추상화 되어있고, response_type에 대해서도 추상화 되어있다.  
그러나 grant_type에 대해서는 request객체를 매개변수로 넘긴다.  
필요한 값을 매개변수가 아니라 request자체를 넘기는 방법도 고려해봐야 한다.  


#### 2020. 07. 15
범용적으로 사용하기 위해 모듈로 만들어야 한다.  
모듈로 만들고 그 모듈을 spring boot에서 불러와서 인증서버를 구현해야 한다.  
따라서 현재는 내부 모듈로 작성하여 만들고, 추후에 모듈을 분리하여 따로 저장소로 올린다.  
그 후 현재 spring boot로 되어있는 것은 해당 모듈의 spring boot 사용 예시로 남겨둔다.  


#### 2020. 07. 16
인터페이스로 만들자.  
범용적으로 사용하기 위해선 인터페이스로 만들고 해당 모듈을 사용하는 곳에서 인터페이스 기반으로 구현해야 한다.  

#### 2020. 07. 18
모듈(라이브러리)을 먼저 만들고 이 프로젝트는 그 모듈을 spring boot로 구현한 프로젝트로 변경 하기로 하였다.  
다시 돌아올 날을 기약하며 이 프로젝트는 잠시 중단한다.  

#### 2020. 07. 30
라이브러리를 개발하여 maven repository에 업로드 하였다.  
다시 구현하면서 라이브러리도 추가해야할 부분은 추가하자.  

#### 2020. 08. 05
라이브러리에서는 테이블에 username을 넣었지만 여기에선 memberId를 넣었다.  
라이브러리의 TokenDto에 expires in 값이 있음. 이 값은 토큰의 만료까지의 시간을 나타낸다.  
하지만 만료 시간을 정확하게 출력해줘야 하지않나 싶다.  (그러나 카카오는 expires in을 반환한다.)  

#### 2020. 08. 09
관리자 페이지를 만들자.  
관리자 전용 api를 만들자.  
관리자 전용 api는 url을 어떻게 해야할지  

제네릭 타입을 추가하였다.  
custom repository를 만들어 library를 구현하였고, jpaRepository와 함께 쓸 수 있도록 하였다.  

#### 2020. 08. 10
ModelMapper를 이용하여 Entity Dto간 타입 변환을 한다.  
일단 스프링 예제 페이지에서 나온 것처럼 컨트롤러에서 하였다. 그러나 서비스에서 해도 될것 같아 고민이 필요하다.  

spring security로 로그인 부분과 api의 인증 부분을 처리 할 수 있는지 확인 후 적용 예정.  

#### 2020. 08. 12
spring security로 적용 중이다.  
security config에서 아래와 같이 두가지로 사용하는 경우를 확인 하였다.  
```
auth.userDetailsService(userDetailsService);
auth.authenticationProvider(authenticationProvider);
```
스프링 시큐리티 인증 절차는 authenticationProvider authenticate 메서드에서  
userDetailsService의 loadUserByUsername메서드에서 반환된 userDetail를 받아  
입력된 아이디 패스워드와 검증한다.  

따라서 인증 절차 자체를 커스터마이징 할게 아닌, 유저를 가져오는 방식만 변경 할것이기 때문에 userDetailsService를 구현하였다.  

패키지명에 대해 고민이 필요하다.  
현재 사용자, 인증, 관리자가 존재한다.  

TODO: 권한에 따라 페이지 접속하도록 해야한다.  
로그인 시 권한을 넣어주고 사용할 수 있도록  

#### 2020. 08. 13
authority와 role  
authority는 기능 단위 이고 role은 여러 권한을 가지는 포괄적인 의미이다.  
예)  
authority : WRITE, READ  
role : ROLE_ADMIN, ROLE_USER  
따라서 role은 prefix로 ROLE_을 추가해야 한다.  

#### 2020. 08. 14
spring security 멀티 로그인을 구현하였다.  
oauth2 사용자와 oauth를 관리하는 관리자는 다른 저장공간에 저장되어야 하기 때문  
순환 참조를 피하기위해 field injection 보다 constructor injection을 활용하자  

#### 2020. 08. 15
멀티로그인을 구현하였으나, (유저, 관리자)  
유저 로그인 후 관리자 페이지로 이동 시 에러 페이지 발생함  
관리자 로그인 페이지로 가도록 해야함  

#### 2020. 08. 20
일단 관리자 페이지까지 완성하였다.  
회원가입과 클라이언트 등록 페이지가 필요하다.  


#### 2020. 08. 21
회원가입은 만들었으나 패스워드 암호화 해서 넣어야 한다.  
그리고 맨처음 데이터도 암호화 한 상태로 넣는 법을 찾아야 한다.  
회원가입시 오류(아이디 형식 오류, 중복, 유효성 검사)는 아직은 추가하지 않을 예정이다.  

