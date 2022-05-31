package com.travel.service;

import com.travel.entity.Customer;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.CustomerDTO;

public interface CustomerService {
	
	//public CustomerDTO loginCustomer(String userName, String password) throws InvalidLoginException;
	public Customer registerCustomer( CustomerDTO customerDto)throws UserNameAlreadyExistsException;
    public Customer editProfile(CustomerDTO customerDTO) throws UserNotFoundException;
    public CustomerDTO viewProfile(int userId)throws UserNotFoundException;
    public  String deleteCustomer(int userId)throws UserNotFoundException;
	
}
