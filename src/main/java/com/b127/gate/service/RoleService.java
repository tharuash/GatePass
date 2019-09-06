package com.b127.gate.service;

import java.util.List;

import com.b127.gate.entity.Role;

public interface RoleService {
	
	List<Role> findAll();

    Role findByRoleName(String roleName);

    void delete(int id);

    Role insert(Role role);
}
