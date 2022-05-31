package com.travel.serviceimpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.entity.Customer;
import com.travel.exception.CustomerNotFoundException;
import com.travel.model.CustomerDTO;
import com.travel.repo.CustomerRepository;
import com.travel.service.CustomerService;
import com.travel.util.ConverterClass;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger LogCustomer = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ConverterClass converter;

	@Override
	public Customer addCustomer(CustomerDTO customerDto) {

		return customerRepository.save(converter.convertToCustomerEntity(customerDto));
	}

	@Override
	public String deleteCustomer(int userId) throws CustomerNotFoundException {

		Optional<Customer> Customer = customerRepository.findById(userId);
		String message = null;
		if (Customer.isPresent()) {
			customerRepository.deleteById(userId);
			message = "customer Deleted Successfully";
			LogCustomer.info(message);

		} else {
			LogCustomer.error("No Customer Found");
			throw new CustomerNotFoundException(message);
		}
		return message;

	}

	@Override
	public CustomerDTO viewCustomer(int userId) throws CustomerNotFoundException {

		Optional<Customer> Customer = customerRepository.findById(userId);
		CustomerDTO dto = null;
		Customer cus = null;
		if (Customer.isPresent()) {
			cus = Customer.get();
			dto = converter.convertToCustomerDTO(cus);

		} else {
			LogCustomer.error("No such Customer Found");
			throw new CustomerNotFoundException("No such Customer Found");
		}
		return dto;

	}

	@Override
	public Customer modifyCustomer(CustomerDTO customerDTO) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(customerDTO.getUserId());
		Customer customerRecord = null;
		if (customer.isPresent()) {
			customerRecord = customer.get();
			customerRepository.save(converter.convertToCustomerEntity(customerDTO));

		} else {
			LogCustomer.error("Customer Not Found");
			throw new CustomerNotFoundException("Customer Not Found");
		}

		return customerRecord;

	}
}
