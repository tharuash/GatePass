package com.b127.gate.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.b127.gate.dto.RegisterUser;
import com.b127.gate.entity.Role;
import com.b127.gate.entity.User;
import com.b127.gate.repository.UserRepository;
import com.b127.gate.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User insert(RegisterUser registerUser) {
		if (userRepository.existsByUsername(registerUser.getUsername())) {
			return null;
		}

		if (userRepository.existsByEmail(registerUser.getEmail())) {
			return null;
		}

		// Creating user account
		User user = new User( registerUser.getUsername(), registerUser.getEmail(), registerUser.getIdNo(),
				encoder.encode(registerUser.getPassword()));
		
		user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		
		return userRepository.save(user);
	}

}
