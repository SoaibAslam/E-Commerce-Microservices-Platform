package com.soaib.authms.controller;

import com.soaib.authms.service.AuthService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/register")
	public String register(@RequestParam String mobile, @RequestParam String password) {
		return authService.register(mobile, password);
	}

	@PostMapping("/verify")
	public String verify(@RequestParam String mobile, @RequestParam String otp) {
		return authService.verifyOtp(mobile, otp);
	}

	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> request) {

		String mobile = request.get("mobile");
		String password = request.get("password");

		return authService.login(mobile, password);
	}
}