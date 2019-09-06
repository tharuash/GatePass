package com.b127.gate.service;

import java.util.List;

import com.b127.gate.dto.RegisterPass;
import com.b127.gate.entity.Pass;

public interface PassService {
	List<Pass> findAll();

	Pass findById(int id);

	void delete(int id);

	Pass insert(RegisterPass registerPass, int userId);

	List<Pass> findAllByUserId(int UserId);
	
	Pass update(Pass pass);
}
