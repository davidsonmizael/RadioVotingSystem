package com.radiolossantos.userservice.service;

import com.radiolossantos.userservice.entity.User;

public interface UserService {

	public User createOrFind(String nickname);
	
	public User findUserById(Long id);

}
