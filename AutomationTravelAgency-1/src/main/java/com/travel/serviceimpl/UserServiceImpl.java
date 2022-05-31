package com.travel.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.User;
import com.travel.exception.UserNotFoundException;
import com.travel.repo.UserRepository;
import com.travel.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User validateUser(String userName, String password) {
		
		User opUser=userRepository.findByUserNameAndPassword(userName ,password);
		if(opUser==null) {
			throw new UserNotFoundException("Invalid User");
		}
		return opUser;
	}
	
	
	

}
