package com.suinautant.myhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.suinautant.myhome.model.User;

//public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
public interface UserRepository extends JpaRepository<User, Long> {

	// EntityGraph : FetchType 무시하고 모두 조회
	// join 으로 모든 연관 테이블 한 번에 조회
	@EntityGraph(attributePaths = { "boards" })
	List<User> findAll();

	User findByUsername(String username);

	// select u -> select *
	// %?1 : 앞 검색
	// %?1% : 앞 뒤 검색
	// ?1 : 첫 번째 파라메터
	@Query("select u from User u where u.username like %?1%")
	List<User> findByUsernameQuery(String username);

	@Query(value = "select * from User u where u.username like %?1%", nativeQuery = true)
	List<User> findByUsernameNativeQuery(String username);

}
