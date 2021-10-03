package com.suinautant.myhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suinautant.myhome.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
