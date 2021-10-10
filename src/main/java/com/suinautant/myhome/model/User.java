package com.suinautant.myhome.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;


import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private Boolean enabled;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();
	
	// cascade : User 객체로 CRUD시 Board 객체도 함께 CRUD 할 때 사용
	// orphanRemoval : 부모가 없는 객체는 삭제 (true)
	//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	// fetch 기본값 LAZY
	// LAZY : OneToMany, ManyToMany : board를 사용할 때만 호출
	// EAGER : OneToOne, ManyToOne : board를 사용하지 않아도 호출
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Board> boards = new ArrayList<>();

}
