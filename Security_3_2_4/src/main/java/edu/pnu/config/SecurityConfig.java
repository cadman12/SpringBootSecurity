package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		
		http.authorizeHttpRequests(auth->auth
			.requestMatchers("/user/**").authenticated()
			.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll()
		);
		
		// 메모리 기반 h2 Database를 사용할 때 "localhost에서 연결을 거부했습니다." 메시지가 뜰 때 설정
		http.headers(header->header.frameOptions(fo->fo.disable()));

		http.formLogin(form->form.loginPage("/login").defaultSuccessUrl("/"));
		http.logout(logout->logout.clearAuthentication(true).deleteCookies("JSESSIONID").logoutSuccessUrl("/"));
		
		return http.build();
	}
}

