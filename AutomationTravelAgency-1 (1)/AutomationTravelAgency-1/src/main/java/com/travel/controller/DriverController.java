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

import com.travel.entity.Driver;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.DriverDTO;
import com.travel.service.DriverService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class DriverController {

	@Autowired
	private DriverService driverService;
	
	@PostMapping(value = "/registerDriver")
	public Driver registerDriver(@RequestBody DriverDTO driverDto) throws UserNameAlreadyExistsException {
	return driverService.registerDriver(driverDto);
	}
	
	@PutMapping(value = "/editDriver")
	public  Driver editProfile(@RequestBody @Valid  DriverDTO driverDto) throws UserNotFoundException{
		return driverService.editProfile(driverDto);
	}
	
	@DeleteMapping(value = "/deleteDriver/{id}")
	public String deleteProfile(@PathVariable("id") int userId) throws  UserNotFoundException {
		return driverService.deleteDriver(userId);
	}
	
	@GetMapping(value = "/viewDriver/{id}")
	public DriverDTO viewProfile(@PathVariable("id") int userId) throws  UserNotFoundException{
		return driverService.viewProfile(userId);
	}

}
