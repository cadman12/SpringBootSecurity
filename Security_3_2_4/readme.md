# MVC를 이용한 Security 실습 코드

## DI

	- spring-boot-starter : data-jpa, security, thymeleaf, web

	- tools : h2, lombok, devtools

## application.properties

	spring.datasource.driver-class-name=org.h2.Driver
	spring.datasource.url=jdbc:h2:mem:test
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
	spring.jpa.hibernate.ddl-auto=create
	spring.jpa.show-sql=true
	spring.thymeleaf.cache=false
	spring.h2.console.enabled=true

## 사용자 데이터

	- Entity Class : Member.java
	
	- 초기 데이터 생성
	
		. ApplicationRunner를 구현한 클래스를 등록해줌으로써 서버 실행 시에 자동으로 입력되도록 설정

## SecurityConfig.java

	- SecurityFilterChain 객체를 설정해서 빈 객체로 등록

	- [Post]/login을 시도하면 Security가 요청을 가로채서 UserDetailService의 loadUserByUsername() 메소드에서 데이터베이스에 있는 사용자 정보를 로딩한다.

	- 로그인에 성공하면 서버 세션에 User 객체가 생성되어서 로딩된다.
	
	- orElseThrows() 메소드를 사용하면서, DB에서 사용자 정보를 로딩하는데 실패하게 되면, NoSuchElementException이 발생된다.