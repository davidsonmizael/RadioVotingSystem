package com.radiolossantos.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.radiolossantos.userservice.dao.UserDAO;
import com.radiolossantos.userservice.entity.User;
import com.radiolossantos.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User createOrFind(String nickname) {
		User user = userDAO.findByNickname(nickname);
		if(null != user) {
			return user;
		}
		return userDAO.save(new User(nickname));
	}

	@Override
	public User findUserById(Long id) {
		return userDAO.findById(id).orElse(null);
	}

}
