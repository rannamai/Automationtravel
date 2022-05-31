package com.travel.service;

import com.travel.entity.Driver;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.DriverDTO;

public interface DriverService {

	public Driver registerDriver( DriverDTO driverDto)throws UserNameAlreadyExistsException;
    public Driver editProfile(DriverDTO driverDtO) throws UserNotFoundException;
    public DriverDTO viewProfile(int userId)throws UserNotFoundException;
    public  String deleteDriver(int userId)throws UserNotFoundException;
	
}
