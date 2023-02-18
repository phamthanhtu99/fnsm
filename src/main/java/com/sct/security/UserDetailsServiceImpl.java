package com.sct.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sct.Entity.UserEntity;
import com.sct.dao.UserDao;

@Service
public  class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserEntity customer = userDao.findUser(username).get(0);
		if (customer == null) {
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		}
		return new SecurityUser(customer);
	}
}
