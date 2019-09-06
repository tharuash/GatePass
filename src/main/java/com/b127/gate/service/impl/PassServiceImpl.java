package com.b127.gate.service.impl;

import java.sql.Timestamp;
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
		return passRepository.findAll();
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
	public Pass insert(RegisterPass registerPass, int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
		String[] date = registerPass.getDate().split("-");
		String[] time = registerPass.getTime().split(":");
		Pass pass = new Pass();
		pass.setUser(user);
		pass.setIssuedTime(new Timestamp(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]),
				Integer.valueOf(time[0]), Integer.valueOf(time[1]), 0, 0));
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

}
