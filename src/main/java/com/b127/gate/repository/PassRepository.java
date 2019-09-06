package com.b127.gate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.b127.gate.entity.Pass;

public interface PassRepository extends JpaRepository<Pass, Integer> {

	List<Pass> findAllByUserId(int userId);

}
