package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Driver;
import com.travel.exception.DriverNotFoundException;
import com.travel.model.DriverDTO;
import com.travel.repo.DriverRepository;
import com.travel.service.DriverService;
import com.travel.util.ConverterClass;

@Service
public class DriverServiceImpl implements DriverService {

	private static Logger logDriver = LoggerFactory.getLogger(DriverServiceImpl.class);
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private ConverterClass converter;

	@Override
	public Driver addDriver(DriverDTO driverDto) {
		return driverRepository.save(converter.convertToDriverEntity(driverDto));
	}

	@Override
	public Driver deleteDriver(int userId) throws DriverNotFoundException {
		Optional<Driver> driver = driverRepository.findById(userId);
		String message = null;
		Driver dri = null;
		if (driver.isPresent()) {
			driverRepository.deleteById(userId);
			message = "Driver Deleted Successfully";
			logDriver.info(message);

		} else {
			message = "No Driver Found";
			logDriver.error(message);
			throw new DriverNotFoundException(message);
		}
		return dri;
	}

	@Override
	public DriverDTO viewDriver(int userId) throws DriverNotFoundException {
		Optional<Driver> Driver = driverRepository.findById(userId);
		DriverDTO dto = null;
		Driver dri = null;
		if (Driver.isPresent()) {
			dri = Driver.get();
			dto = converter.convertToDriverDTO(dri);

		} else {
			logDriver.error("No such Driver Found");
			throw new DriverNotFoundException("No such Driver Found");
		}
		return dto;
	}

	@Override
	public Driver modifyDriver(DriverDTO driverDto) {
		Optional<Driver> Driver = driverRepository.findById(driverDto.getUserId());
		Driver driverRecord = null;
		if (Driver.isPresent()) {
			driverRecord = Driver.get();
			driverRepository.save(converter.convertToDriverEntity(driverDto));

		} else {
			logDriver.error("Driver Not Found");
			throw new DriverNotFoundException("Driver Not Found");
		}

		return driverRecord;

	}

}
