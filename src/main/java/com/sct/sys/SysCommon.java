package com.sct.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sct.Entity.UserEntity;
import com.sct.Service.UserService;
import com.sct.Utils.Auth;
import com.sct.Utils.Auth.Role;
import com.sct.Utils.Result;
import com.sct.Utils.SessionUtils;
import com.sct.dao.UserDao;
import com.sct.dto.RoleDTO;
import com.sct.dto.UserDTO;

@RestController
public class SysCommon {
	@Autowired
	UserDao user;
	
	@Autowired
	UserService userSv;

	@Auth(role = Role.LOGIN)
	@PostMapping("/login")
	public Result authencate (@RequestBody UserDTO dto,HttpServletRequest request){
		Result result = new Result();
		
		try {
			int chk = userSv.checklogin(dto, request);
			result.setRsmap("LOGIN_CHK", chk);
			
		}catch(BadCredentialsException ex){
			result.setMSG("FAIL");
		}
		
		return result;
	
	}
	
	@GetMapping("/logout")
	public Result authencate (HttpServletRequest request){
		Result result = new Result();
		HttpSession session = request.getSession();
		session.invalidate();
		return result;
	}
	
	@Auth(role = Auth.Role.ADMIN)
	@PostMapping("/add_user")
	public Result add(@RequestBody UserEntity dto) {
		Result result = new Result();
		try {
			userSv.add_user(dto);
		} catch (Exception e) {
			result.setMSG("FAIL");
		}
		
		return result;
	}
	
	@Auth(role = Role.LOGIN)
	@PostMapping("/check_otp")
	public Result check_otp(@RequestBody Map<String, String> map,HttpServletRequest request){
		Result result = new Result();
		
		if (userSv.check_otp(map.get("OTP"), request)) {
			List<RoleDTO> roleDTOs =(List<RoleDTO>) SessionUtils.getSession("role", request);
			result.setRsmap("role", roleDTOs);
			result.setRsmap("MES", true);
		}else {
			result.setRsmap("MES", false);
		}
		
		return result;
	}
	
	
}
