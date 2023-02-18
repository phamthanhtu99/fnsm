package com.sct.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sct.Entity.RoleEntity;
import com.sct.Entity.UserEntity;



public class SecurityUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6690946490872875352L;

	private UserEntity customer;

	public SecurityUser(UserEntity customer) {
		this.customer = customer;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : customer.getRoleEntity()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return customer.getPass();
	}

	@Override
	public String getUsername() {
		return customer.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
