package com.b127.gate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b127.gate.entity.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(String roleName);
}
