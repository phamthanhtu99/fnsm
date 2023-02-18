package com.sct.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sct.Utils.Auth;
import com.sct.Utils.Auth.Role;

@Controller
public class ComController {
	
	@Auth(roles = {Role.ADMIN,Role.USER})
	@GetMapping("/home")
	public String home(@RequestParam("path")String path) {
		String url ="admin/home";
		return url;
	}
}
