package com.b127.gate.service;

import java.text.ParseException;
import java.util.List;

import com.b127.gate.dto.RegisterPass;
import com.b127.gate.entity.Pass;

public interface PassService {
	List<Pass> findAll();

	Pass findById(int id);

	void delete(int id);

	Pass insert(RegisterPass registerPass, int userId) throws ParseException;

	List<Pass> findAllByUserId(int UserId);

	Pass update(Pass pass);

	List<Pass> findAllByIssuedDate() throws ParseException;

	List<Pass> findAllByUser(String username);
	
	List<Pass> findAllByUserAndDate(String date, int userId);
}
