package com.b127.gate.service;

import java.util.List;

import com.b127.gate.dto.RegisterUser;
import com.b127.gate.entity.User;


public interface UserService {
	
	List<User> findAll();

    User findById(int id);

    void delete(int id);

    User insert(RegisterUser registerUser);

}
