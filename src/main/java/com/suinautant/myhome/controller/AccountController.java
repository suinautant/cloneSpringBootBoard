package com.suinautant.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suinautant.myhome.model.User;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@GetMapping("/login")
	public String login() {
		return "/account/login.html";
	}
	
	@PostMapping("/register")
	public String register(User user) {
		return "/account/register.html";
	}

}
