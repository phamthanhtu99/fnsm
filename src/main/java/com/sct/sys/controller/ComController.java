package com.sct.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sct.Utils.Auth;
import com.sct.Utils.Auth.Role;

@Controller
public class ComController {
	
	@Auth(roles = {Role.ADMIN,Role.USER})
	@GetMapping("/home")
	public ModelAndView home(@RequestParam("path")String path) {
		ModelAndView view = new ModelAndView("admin/infor");
		view.addObject("test", "ok");
		return view;
	}
}
