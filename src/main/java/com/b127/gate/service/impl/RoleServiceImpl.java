package com.b127.gate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b127.gate.entity.Role;
import com.b127.gate.repository.RoleRepository;
import com.b127.gate.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findByRoleName(String roleName) {
		return roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("No role"));
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Role insert(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
