package com.travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.entity.User;
import com.travel.exception.InvalidLoginException;
import com.travel.model.UserDTO;
import com.travel.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/login")
	public UserDTO validateUser(@RequestBody @Valid String userName, String password) throws InvalidLoginException {
		User user = null;
	 userName=user.getUserName();
	 password=user.getPassword();
	return userService.validateUser(userName, password);
	} 
}
