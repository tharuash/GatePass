package com.b127.gate.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.b127.gate.entity.SecuredUser;
import com.b127.gate.entity.User;
import com.b127.gate.repository.UserRepository;
import com.b127.gate.security.service.SecuredUserDetailsService;

@Service
public class SecuredUserDetailsServiceImpl implements SecuredUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("user Not found with -> username or email : " + username));

		return new SecuredUser(user);
	}

}
