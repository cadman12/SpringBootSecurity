# JWT를 이용한 Security 실습 코드

## DI

	- spring-boot-starter : data-jpa, security, web
	- tools : h2, lombok, devtools, java-jwt

## application.properties

	spring.datasource.driver-class-name=org.h2.Driver
	spring.datasource.url=jdbc:h2:mem:test
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
	spring.jpa.hibernate.ddl-auto=create
	spring.jpa.show-sql=true
	spring.h2.console.enabled=true

## 사용자 데이터

	- Entity Class : Member.java
	- 초기 데이터 생성
		. ApplicationRunner를 구현한 클래스를 등록해줌으로써 서버 실행 시에 자동으로 입력되도록 설정

## SecurityConfig.java

	- SecurityFilterChain 객체를 설정해서 빈 객체로 등록
		. 접근권한, csrf, 세션 설정, 필터 추가 등
	- [Post]/login을 시도하면 JWTAuthenticationFilter에서 요청을 가로채서 로그인을 진행(by AuthenticationManager의 authenticate())
	- 로그인에 성공하면 토큰을 만들어서 응답 헤더(Authorization)에 저장하고 응답 코드를 OK(200:HttpStatus.OK.value())로 설정한 뒤 리턴
	- 로그인에 실패하면 응답 코드를 UNAUTHORIZED(401:HttpStatus.UNAUTHORIZED.value())로 설정한 뒤 리턴
	- 로그인 성공유무와 상관없이 더이상 진행될 필요가 없으므로 FilterChain을 타지 않는다.
	
	- 프런트앤드가 헤더에 JWT를 담아서 요청하면 JWTAuthorizationFilter에서 요청을 가로채서 JWT가 정당한지 여부를 검증
	- 검증이 정상적으로 수행되면 세션에 User 객체를 생성해서 로딩, 이 객체는 응답이 완료되면 삭제한다. (stateless)
	- 검증 여부와 상관없이 필터체인을 타도록 한다. (토큰 없잉 접근할 수 있는 URL도 있으므로 검증안되었다고 그냥 리턴하면 안됨)