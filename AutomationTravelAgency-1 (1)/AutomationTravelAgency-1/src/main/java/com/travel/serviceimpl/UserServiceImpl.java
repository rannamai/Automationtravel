package com.travel.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.User;
import com.travel.exception.InvalidLoginException;
import com.travel.model.UserDTO;
import com.travel.repo.UserRepository;
import com.travel.service.UserService;
import com.travel.util.ConversionClass;

@Service
public class UserServiceImpl implements UserService 
{
	
	private static Logger logUser = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private ConversionClass conversionClass;

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public UserDTO validateUser(String userName, String password) throws InvalidLoginException{
		
		User opUser=userRepository.findByUserNameAndPassword(userName ,password);
		UserDTO user=null;
		if(opUser==null)
		{
			logUser.error("Invalid User");
			throw new InvalidLoginException("Invalid User");
		}
		else
		{
			user=conversionClass.convertToUserDTO(opUser);
		}
		return user;
	}
	
//	@Override
//	public User loginCustomer(String userName, String password) throws InvalidLoginException {
//		System.out.println(userName+" "+password);
//		User user=userRepository.findByUserNameAndPassword(userName, password);
//		
//		if(user==null) { 
//			throw new InvalidLoginException("No such Customer");
//		}
//		
//		
//		return user;
//	}

}
