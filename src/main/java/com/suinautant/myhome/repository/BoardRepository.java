package com.suinautant.myhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suinautant.myhome.model.Board;

public interface BoardRepository  extends JpaRepository<Board, Long> {

}
