package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.pnu.config.filter.JWTAuthenticationFilter;
import edu.pnu.config.filter.JWTAuthorizationFilter;
import edu.pnu.repository.MemberRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private OAuth2SuccessHandler oauth2SuccessHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(csrf->csrf.disable());
		http.cors(cors->cors.configurationSource(corsSource()));
		
		http.authorizeHttpRequests(auth->{
			auth.requestMatchers("/user/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll();
		});
		
		http.sessionManagement(ssmn->ssmn.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.formLogin(frm->frm.disable());
		http.httpBasic(basic->basic.disable());
		
		http.oauth2Login(oauth2->oauth2.successHandler(oauth2SuccessHandler));
		
		http.addFilter(new JWTAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(memberRepo), AuthorizationFilter.class);

		// 메모리 기반 h2 Database를 사용할 때 "localhost에서 연결을 거부했습니다." 메시지가 뜰 때 설정
		http.headers(header->header.frameOptions(fo->fo.disable()));
		
		return http.build();
	}
    
    private CorsConfigurationSource corsSource() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.addAllowedOriginPattern(CorsConfiguration.ALL);
    	config.addAllowedMethod(CorsConfiguration.ALL);
    	config.addAllowedHeader(CorsConfiguration.ALL);
    	config.addExposedHeader(CorsConfiguration.ALL);

    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", config);
    	return source;
    }
}

