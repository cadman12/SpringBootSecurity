package edu.pnu.service;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import edu.pnu.config.auth.AuthUser;
import edu.pnu.domain.Member;

@Component
public class OAuth2UserDetailService extends DefaultOAuth2UserService {
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		Map<String, Object> map = user.getAttributes();
		
		Member member = Member.builder()
				.username((String)map.get("sub"))
				.password("[PROTECTED]")
				.enabled(true)
				.role("ROLE_USER")
				.build();
		return new AuthUser(member, map);
	}
}
