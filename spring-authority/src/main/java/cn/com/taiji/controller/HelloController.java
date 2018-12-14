package cn.com.taiji.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String getWelcome() {
		return "hello";
	}

	@GetMapping("/helloUser")
	@PreAuthorize("hasAnyRole('USER')")
	public String getHelloUser() {
		return "hello,User";
	}

	@GetMapping("/helloAdmin")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String getHelloAdmin() {
		return "hello,Admin";
	}
}
