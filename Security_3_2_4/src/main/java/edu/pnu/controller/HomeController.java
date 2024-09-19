package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/test")
	public String testPost() {
		return "<h2>test</h2>";
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h2>user</h2>";
	}

	@GetMapping("/manager")
	public String manager() {
		return "<h2>manager</h2>";
	}

	@GetMapping("/admin")
	public String admin() {
		return "<h2>admin</h2>";
	}
}
