package com.b127.gate.service.impl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b127.gate.dto.RegisterPass;
import com.b127.gate.entity.Pass;
import com.b127.gate.entity.User;
import com.b127.gate.repository.PassRepository;
import com.b127.gate.repository.UserRepository;
import com.b127.gate.service.PassService;

@Service
public class PassServiceImpl implements PassService {

	@Autowired
	PassRepository passRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<Pass> findAll() {
		return passRepository.findAllByOrderByIdAsc();
	}

	@Override
	public Pass findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pass insert(RegisterPass registerPass, int userId) throws ParseException {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
		LocalDate date = LocalDate.parse(registerPass.getDate());
		LocalTime time = LocalTime.parse(registerPass.getTime());

		Pass pass = new Pass();
		pass.setUser(user);
		pass.setIssuedDate(date);
		pass.setIssuedTime(time);

		return passRepository.save(pass);
	}

	@Override
	public List<Pass> findAllByUserId(int userId) {
		List<Pass> list = passRepository.findAllByUserId(userId);
		return list;

	}

	@Override
	public Pass update(Pass pass) {
		Pass updatingPass = passRepository.getOne(pass.getId());
		updatingPass.setIsAccepted(pass.getIsAccepted());
		return passRepository.save(updatingPass);
	}

	@Override
	public List<Pass> findAllByIssuedDate() throws ParseException {

		return passRepository.findAllByIssuedDate(LocalDate.now());
	}

	@Override
	public List<Pass> findAllByUser(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
		return passRepository.findAllByUser(user);
	}

	@Override
	public List<Pass> findAllByUserAndDate(String date, int userId) {
		User user = userRepository.getOne(userId);
		LocalDate issuedDate = LocalDate.parse(date);
		
		return passRepository.findAllByUserAndIssuedDate(user, issuedDate);
	}

	

}
