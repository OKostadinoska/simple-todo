package com.todo.springboot.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todo.springboot.model.AuthenticationType;
import com.todo.springboot.model.Customer;
import com.todo.springboot.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	PasswordEncoder passwordEncoder;

	public void registerCustomer(Customer customer) {
		encodePassword(customer);
		customer.setEnabled(false);

		customer.setAuthenticationType(AuthenticationType.DATABASE);

		customerRepo.save(customer);

	}

	public Customer getCustomerByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	private void encodePassword(Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}

	public void updateAuthenticationType(Customer customer, AuthenticationType type) {
		if (!customer.getAuthenticationType().equals(type)) {
			customerRepo.updateAuthenticationType(customer.getId(), type);
		}
	}

}
