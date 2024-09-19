package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class WebCustomConfig {

	private final AuthenticationConfiguration authConfig;

	@Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authConfig.getAuthenticationManager();
	}    
    
    @Bean
    CorsConfigurationSource corsSource() {
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

