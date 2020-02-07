package com.radiolossantos.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.radiolossantos.userservice.dto.UserDTO;
import com.radiolossantos.userservice.entity.User;
import com.radiolossantos.userservice.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("{id}")
	public @ResponseBody User getUser(@PathVariable Long id) {
		return userService.findUserById(id);
	}
	
	@PostMapping
	public @ResponseBody User newUser(@RequestBody UserDTO newUser) {
		return userService.createOrFind(newUser.getNickname());
	}

}
