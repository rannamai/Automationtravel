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

import com.travel.entity.Admin;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.AdminDTO;
import com.travel.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost", maxAge = 3600)
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping(value = "/registerAdmin")
	public Admin registerAdmin(@RequestBody AdminDTO adminDto) throws UserNameAlreadyExistsException {
	return adminService.registerAdmin(adminDto);
	}
	
	@PutMapping(value = "/editAdmin")
	public  Admin editProfile(@RequestBody @Valid  AdminDTO adminDto) throws UserNotFoundException{
		return adminService.editProfile(adminDto);
	}
	
	@DeleteMapping(value = "/deleteAdmin/{id}")
	public String deleteProfile(@PathVariable("id") int userId) throws  UserNotFoundException {
		return adminService.deleteAdmin(userId);
	}
	
	@GetMapping(value = "/viewAdmin/{id}")
	public AdminDTO viewProfile(@PathVariable("id") int userId) throws  UserNotFoundException{
		return adminService.viewProfile(userId);
	}

	


}
