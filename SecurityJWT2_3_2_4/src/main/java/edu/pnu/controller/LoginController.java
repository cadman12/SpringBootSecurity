package edu.pnu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

	private final AuthenticationManager authenticationManager;	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginPost(@RequestBody Member member) {
		System.out.println("member:" + member);

		try {		
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			authenticationManager.authenticate(authToken);
		} catch (Exception e) {
			log.info(e.getMessage());	// 자격 증명에 실패하였습니다.
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		String jwtToken = JWTUtil.getJWT(member.getUsername());
		
		System.out.println(jwtToken);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", jwtToken);
		
		return ResponseEntity.ok().header("Authorization", jwtToken).build();
	}
}
