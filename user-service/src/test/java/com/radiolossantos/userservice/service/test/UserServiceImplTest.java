package com.radiolossantos.userservice.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.radiolossantos.userservice.dao.UserDAO;
import com.radiolossantos.userservice.entity.User;
import com.radiolossantos.userservice.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserDAO userDAO;
	
	@InjectMocks
	@Spy
	private UserServiceImpl userService;
	

	@BeforeEach
	public void setup() {

	
	}

	
	@Test
	public void testCreateOrFind() {
		User testUser = new User();
		testUser.setId(100l);
		testUser.setNickname("testUser");
		
		User newUser = new User();
		newUser.setId(200l);
		newUser.setNickname("testUser2");
		
		when(userDAO.findByNickname("testUser")).thenReturn(testUser);
		when(userDAO.findByNickname("testUser2")).thenReturn(null);
		when(userDAO.save(new User("testUser2"))).thenReturn(newUser);
		
		assertEquals(userService.createOrFind("testUser"), testUser);
		assertNotNull(userService.createOrFind("testUser2"));
		
	}
	
	@Test
	public void testFindUserById() {
		User testUser = new User();
		testUser.setId(100l);
		testUser.setNickname("testUser");
		
		when(userDAO.findById(100l)).thenReturn(Optional.of(testUser));
		assertEquals(userService.findUserById(100l), testUser);
		
	}
}
