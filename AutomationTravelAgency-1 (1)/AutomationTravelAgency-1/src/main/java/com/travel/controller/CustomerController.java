package com.travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.travel.entity.Customer;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.CustomerDTO;
import com.travel.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping(value = "/registerCustomer")
	public Customer registerCustomer(@RequestBody CustomerDTO customerDto) throws UserNameAlreadyExistsException {
	return customerService.registerCustomer(customerDto);
	}
	
	@PutMapping(value = "/edit")
	public  Customer editProfile(@RequestBody @Valid  CustomerDTO customerDto) throws UserNotFoundException{
		return customerService.editProfile(customerDto);
	}
	
	@DeleteMapping(value = "/deleteCustomer/{id}")
	public String deleteProfile(@PathVariable("id") int userId) throws  UserNotFoundException {
		return customerService.deleteCustomer(userId);
	}
	
	@GetMapping(value = "/viewCustomer/{id}")
	public CustomerDTO viewProfile(@PathVariable("id") int userId) throws  UserNotFoundException{
		return customerService.viewProfile(userId);
	}
	
//	@PostMapping(value="/login")
//	public CustomerDTO loginCustomer(@RequestBody Customer customer) throws InvalidLoginException {
//	String userName=customer.getUserName();
//	String password=customer.getPassword();
//	return customerService.loginCustomer(userName, password);
//	} 
}
