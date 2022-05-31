package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Admin;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.AdminDTO;
import com.travel.repo.AdminRepository;
import com.travel.service.AdminService;
import com.travel.util.ConversionClass;

@Service
public class AdminServiceImpl implements AdminService {

	private static Logger logAdmin = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private ConversionClass converter;

	@Override
	public Admin registerAdmin(AdminDTO adminDto) throws UserNameAlreadyExistsException {
		if(this.adminRepo.existsByUserName(adminDto.getUserName())) {
			 logAdmin.error("UserName already exist try new one");
		        throw new UserNameAlreadyExistsException("UserName already exist try new one");
		 }
		return adminRepo.save(converter.convertToAdminEntity(adminDto));
	}

	@Override
	public Admin editProfile(AdminDTO adminDTO) throws UserNotFoundException {
		
		Optional<Admin> admin = adminRepo.findById(adminDTO.getUserId());
		Admin adminRecord=null;
		if(admin.isPresent()) {
			 adminRecord= admin.get();
			 adminRepo.save(converter.convertToAdminEntity(adminDTO));
			
		}
		else {
			logAdmin.error("User Not Found");
			
		}
		

		return adminRecord; 
	}

	@Override
	public AdminDTO viewProfile(int userId) throws UserNotFoundException {
		
		Optional<Admin> admin = adminRepo.findById(userId);
		AdminDTO dto=null;
		Admin record = null;
		if(admin.isPresent()) {
			record=admin.get();
			 dto=converter.convertToAdminDTO(record);
			
		}
		else {
			String message="User Not found";
			logAdmin.error(message);
			throw new UserNotFoundException(message);
		}
		return dto;
	}

	@Override
	public String deleteAdmin(int userId) throws UserNotFoundException {
		String message=null;
		Optional<Admin> admin = adminRepo.findById(userId);	
		if(admin.isPresent()) {
			adminRepo.deleteById(userId);
			message="Deleted Successfully";
			logAdmin.info(message);
			
		}
		else {
			 message="User Not found";
			logAdmin.error(message);
			//throw new UserNotFoundException(message);
		}
		return message;
	}
	
}
