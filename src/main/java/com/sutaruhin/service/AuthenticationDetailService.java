package com.sutaruhin.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sutaruhin.entity.Authentication;
import com.sutaruhin.repository.AuthenticationRepository;

@Service
public class AuthenticationDetailService implements UserDetailsService{
	@Autowired
	private AuthenticationRepository authenticationRepository;

	@Override
	public AuthenticationDetail loadUserByUsername(String code) throws UsernameNotFoundException {
		Authentication authentication = authenticationRepository.findByCode(code);

		if(authentication == null) {
			throw new UsernameNotFoundException("Exception:Username Not Found");
		}

		return new AuthenticationDetail(authentication);
	}
}
