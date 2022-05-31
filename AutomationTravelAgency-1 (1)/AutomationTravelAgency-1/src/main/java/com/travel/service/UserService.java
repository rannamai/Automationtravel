package com.travel.service;

import com.travel.exception.InvalidLoginException;
import com.travel.model.UserDTO;

public interface UserService {
	
	
	public UserDTO validateUser(String userName, String password)throws InvalidLoginException;

	//public User loginCustomer(String userName, String password) throws InvalidLoginException;
	
}
