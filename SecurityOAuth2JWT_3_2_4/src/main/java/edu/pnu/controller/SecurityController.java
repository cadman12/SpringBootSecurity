package edu.pnu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class SecurityController {

	@GetMapping("/home")
	public ResponseEntity<?> home(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.setAttribute("Test", "abcd");
		
		System.out.println("Cookie");
		Cookie[] cs = req.getCookies();
		if (cs != null) {
			for (Cookie c : cs) {
				System.out.println(c.getName() + ":" + c.getValue());
			}
		}
		else {
			System.out.println("Cookie is null");
		}
		
		return ResponseEntity.ok("<h1>home GET</h1>");
	}
	
	@GetMapping("/auth")
	public String auth(@AuthenticationPrincipal User user) {
		if (user == null)	return "user is not found!";
		return user.toString();
	}
	
	@GetMapping("/user")	public String user() 	{	return "user";		}
	@GetMapping("/manager")	public String manager() {	return "manager";	}
	@GetMapping("/admin")	public String admin() 	{	return "admin";	}
}
