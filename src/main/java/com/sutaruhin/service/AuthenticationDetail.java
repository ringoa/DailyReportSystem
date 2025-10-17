package com.sutaruhin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sutaruhin.entity.Authentication;

public class AuthenticationDetail implements UserDetails {
	private static final long serialVersionUID = 1L;

	private final Authentication authentication;
	private final Collection<GrantedAuthority> authorities;

	public AuthenticationDetail(Authentication authentication) {
		this.authentication = authentication;
		this.authorities = new ArrayList<GrantedAuthority>();
		this.authorities.add(new SimpleGrantedAuthority(authentication.getRole().name()));
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return authorities;
	}

	@Override
	public String getPassword() {
		return authentication.getPassword();
	}

	@Override
	public String getUsername() {
		return authentication.getCode();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate validDate = authentication.getValidDate();

		if(validDate.isBefore(LocalDate.now())) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isEnabled() {
		int isDelted = authentication.getEmployee().getDeleteFlag();

		if(isDelted == 1) {
			return false;
		}

		return true;
	}
}
