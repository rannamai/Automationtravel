package com.travel.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Customer findByUserNameAndPassword(String UserName, String Password);
	public boolean existsByUserName(String Username);
}
