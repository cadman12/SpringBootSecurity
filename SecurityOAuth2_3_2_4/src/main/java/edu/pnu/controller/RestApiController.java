package edu.pnu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

	@GetMapping("/member")
	public String forMember() {
		return "Member 요청입니다.";
	}
	@GetMapping("/manager")
	public String forManager() {
		return "Manager 요청입니다.";
	}
	@GetMapping("/admin")
	public String forAdmin() {
		return "Admin 요청입니다.";
	}
}