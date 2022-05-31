package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Customer;
import com.travel.exception.UserNameAlreadyExistsException;
import com.travel.exception.UserNotFoundException;
import com.travel.model.CustomerDTO;
import com.travel.repo.CustomerRepository;
import com.travel.service.CustomerService;
import com.travel.util.ConversionClass;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger logCustomer = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ConversionClass converter;

	@Override
	public Customer registerCustomer(CustomerDTO customerDto) throws UserNameAlreadyExistsException {
		if(this.customerRepo.existsByUserName(customerDto.getUserName())) {
			 logCustomer.error("UserName already exist try new one");
		        throw new UserNameAlreadyExistsException("UserName already exist try new one");
		 }
		return customerRepo.save(converter.convertToCustomerEntity(customerDto));
	}

	@Override
	public Customer editProfile(CustomerDTO customerDTO) throws UserNotFoundException {
		
		Optional<Customer> customer = customerRepo.findById(customerDTO.getUserId());
		Customer customerRecord=null;
		if(customer.isPresent()) {
			 customerRecord= customer.get();
			 customerRepo.save(converter.convertToCustomerEntity(customerDTO));
			
		}
		else {
			logCustomer.error("User Not Found");
			
		}
		

		return customerRecord; 
	}

	@Override
	public CustomerDTO viewProfile(int userId) throws UserNotFoundException {
		
		Optional<Customer> customer = customerRepo.findById(userId);
		CustomerDTO dto=null;
		Customer record = null;
		if(customer.isPresent()) {
			record=customer.get();
			 dto=converter.convertToCustomerDTO(record);
			
		}
		else {
			String message="User Not found";
			logCustomer.error(message);
			//throw new UserNotFoundException(message);
		}
		return dto;
	}

	@Override
	public String deleteCustomer(int userId) throws UserNotFoundException {
		String message=null;
		Optional<Customer> customer = customerRepo.findById(userId);
		if(customer.isPresent()) {
			customerRepo.deleteById(userId);
			message="Deleted Successfully";
			logCustomer.info(message);
			
		}
		else {
			 message="User Not found";
			logCustomer.error(message);
			//throw new UserNotFoundException(message);
		}
		return message;
	}

//	@Override
//	public CustomerDTO loginCustomer(String userName, String password) throws InvalidLoginException  {
//Customer customer=customerRepo.findByUserNameAndPassword(userName, password);
//		
//         CustomerDTO dto=null;
//		if(customer==null) { 
//			throw new InvalidLoginException("UserName or password doesn't matches");
//		}
//		else
//		{
//		 dto=converter.convertToCustomerDTO(customer);
//		}
//		
//		
//		return dto;
//	}	
}
