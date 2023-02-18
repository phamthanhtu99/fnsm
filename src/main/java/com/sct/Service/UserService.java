package com.sct.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sct.Entity.RoleEntity;
import com.sct.Entity.UserEntity;
import com.sct.Utils.SessionUtils;
import com.sct.Utils.StatusAcount;
import com.sct.Utils.StringUtils;
import com.sct.Utils.Userinfo;
import com.sct.convert.UserConert;
import com.sct.dao.RoleDao;
import com.sct.dao.UserDao;
import com.sct.dto.RoleDTO;
import com.sct.dto.UserDTO;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager auth;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao dao;

	
	
	@Autowired 
	private RoleDao roledao;
	
	@Autowired
	private UserConert convert;

	public int checklogin(UserDTO dto, HttpServletRequest request) {

		try {
			
			List<Map<String, Object>> resultList = dao.check_login(dto.getUserName());

			if (resultList.size() == 0 || resultList.isEmpty()) {
				return StatusAcount.ACOUNTNOTEXIT.getCode();
			}

			Map<String, Object> map = resultList.get(0);
		
			boolean pwchk = passwordEncoder.matches(dto.getPass(),map.get("PS").toString());

			if (!pwchk) {
				return StatusAcount.ACOUNTFAIL.getCode();
			}

			if ("3".equals(map.get("ST"))) {
				return StatusAcount.ACOUNTLOCK.getCode();
			} 
			
			Userinfo userinfo = Userinfo.builder().id(map.get("ID").toString()).usernam(map.get("UNM").toString()).build();
            SessionUtils.setSessionValue("user", userinfo,request);
			
			List<Map<String,Object>> roles = roledao.findroleByUserID(map.get("ID").toString());
			
			List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
			for (Map<String, Object> item : roles) {
				RoleDTO roleDTO = RoleDTO.builder().name(item.get("NM").toString()).id(Long.valueOf(item.get("ID").toString())).build();
				roleDTOs.add(roleDTO);
			}
			
			SessionUtils.setSessionValue("role", roleDTOs ,request);
			
			String otp = StringUtils.Otp();
			dao.updateOtp(otp, map.get("ID").toString());
		} catch (BadCredentialsException ex) {
			throw new RuntimeException("Username");
		}

		return 0;
	}
	
	public boolean check_otp(String otp,HttpServletRequest request){
		Userinfo userinfo = (Userinfo) SessionUtils.getSession("user", request);
		UserDTO  userDTO = convert.convertDto(dao.findById(Long.valueOf(userinfo.getId())).get());
		if(userDTO.getOpt().equals(otp)){
			SessionUtils.setSessionValue("isLogin", true, request);
			return true;
		}
		return false;	
	}
	
	public void add_user(UserEntity entity) {
		entity.setPass(passwordEncoder.encode(entity.getPass()));
		entity.setUser_id(StringUtils.getUserId());
		entity.setStatus(1);
		//set role 
		List<RoleEntity> roles = new ArrayList<>();
		// role user
		roles.add(new RoleEntity().builder().id(Long.valueOf(1)).build());
		
		entity.setRoleEntity(roles);
		userDao.save(entity);
	}
}
