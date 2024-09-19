package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebCustomConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
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

