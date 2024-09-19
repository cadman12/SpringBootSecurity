package edu.pnu.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.config.auth.AuthUser;

@Controller
public class LoginController {

	@GetMapping("/login")
	public void login() {}

	// OAuth2 로그인 세션 정보 확인
	@GetMapping("/oauth")
	public @ResponseBody String auth(@AuthenticationPrincipal OAuth2User user) {
		if (user == null) {
			return "oAuth2User is Null";
		}
		return user.toString();
	}

	// 로그인 세션 정보 확인
	@GetMapping("/user")
//	public @ResponseBody String user(@AuthenticationPrincipal User user) {
//		if (user == null) {
//			return "User is Null";
//		}
//		return user.toString();
//	}	
	public @ResponseBody String user(@AuthenticationPrincipal Object user) {
		if (user instanceof User u) {
			return u.toString();
		}
		else if (user instanceof OAuth2User u) {
			return u.toString();
		}
		return "unknown";
	}	
	
	// 통합 로그인 세션 정보 확인
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal AuthUser user) {
		if (user == null) {
			return "User is Null";
		}
		return user.toString();
	}
}
