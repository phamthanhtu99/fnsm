package com.sct.sys.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sct.Utils.Auth;
import com.sct.Utils.Auth.Role;

@Controller
public class Login {
	
	
	@GetMapping("/login")
	public String pagelogin(){
		return "login";
	}
	
	@GetMapping("/test")
	public String pagelogin1(){
		return "login";
	}
	
	@Auth(role =Role.LOGIN)
	@PostMapping("/home")
	public String home(@RequestParam(required= true) Map<String, String> map){
		String url = "";
		return "admin/home";
	}
}
