package com.suinautant.myhome.repository;


import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.suinautant.myhome.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	// EntityGraph : FetchType 무시하고 모두 조회
	// join 으로 모든 연관 테이블 한 번에 조회
	@EntityGraph(attributePaths = {"boards"})
	List<User> findAll();

	User findByUsername(String username);

}
