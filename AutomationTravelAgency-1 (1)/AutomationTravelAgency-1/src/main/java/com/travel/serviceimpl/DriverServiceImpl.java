package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Driver;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.DriverDTO;
import com.travel.repo.DriverRepository;
import com.travel.service.DriverService;
import com.travel.util.ConversionClass;

@Service
public class DriverServiceImpl implements DriverService {

private static Logger logDriver = LoggerFactory.getLogger(DriverServiceImpl.class);
	
	@Autowired
	private DriverRepository driverRepo;
	
	@Autowired
	private ConversionClass converter;

	@Override
	public Driver registerDriver(DriverDTO driverDto) throws UserNameAlreadyExistsException {
		if(this.driverRepo.existsByUserName(driverDto.getUserName())) {
			 logDriver.error("UserName already exist try new one");
		        throw new UserNameAlreadyExistsException("UserName already exist try new one");
		 }
		return driverRepo.save(converter.convertToDriverEntity(driverDto));
	}

	@Override
	public Driver editProfile(DriverDTO driverDTO) throws UserNotFoundException {
		
		Optional<Driver> driver = driverRepo.findById(driverDTO.getUserId());
		Driver driverRecord=null;
		if(driver.isPresent()) {
			 driverRecord= driver.get();
			 driverRepo.save(converter.convertToDriverEntity(driverDTO));
			
		}
		else {
			logDriver.error("User Not Found");
			
		}
		

		return driverRecord; 
	}

	@Override
	public DriverDTO viewProfile(int userId) throws UserNotFoundException {
		
		Optional<Driver> driver = driverRepo.findById(userId);
		DriverDTO dto=null;
		Driver record = null;
		if(driver.isPresent()) {
			record=driver.get();
			 dto=converter.convertToDriverDTO(record);
			
		}
		else {
			String message="User Not found";
			logDriver.error(message);
			throw new UserNotFoundException(message);
		}
		return dto;
	}

	@Override
	public String deleteDriver(int userId) throws UserNotFoundException {
		String message=null;
		Optional<Driver> driver = driverRepo.findById(userId);	
		if(driver.isPresent()) {
			driverRepo.deleteById(userId);
			message="Deleted Successfully";
			logDriver.info(message);
			
		}
		else {
			 message="User Not found";
			logDriver.error(message);
			//throw new UserNotFoundException(message);
		}
		return message;
	}

}
