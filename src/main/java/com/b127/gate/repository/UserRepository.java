package com.b127.gate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b127.gate.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
}
