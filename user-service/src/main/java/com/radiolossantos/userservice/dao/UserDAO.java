package com.radiolossantos.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.radiolossantos.userservice.entity.User;

public interface UserDAO extends JpaRepository<User, Long>{
	
	public User findByNickname(String nickname);

}
