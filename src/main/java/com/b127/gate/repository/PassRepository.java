package com.b127.gate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b127.gate.entity.Pass;
import com.b127.gate.entity.User;

public interface PassRepository extends JpaRepository<Pass, Integer> {

	List<Pass> findAllByUserId(int userId);

	List<Pass> findAllByIssuedDate(LocalDate date);

	List<Pass> findAllByUser(User user);
	
	List<Pass> findAllByOrderByIdAsc();
	
	List<Pass> findAllByUserAndIssuedDate(User user, LocalDate issuedDate );

}
