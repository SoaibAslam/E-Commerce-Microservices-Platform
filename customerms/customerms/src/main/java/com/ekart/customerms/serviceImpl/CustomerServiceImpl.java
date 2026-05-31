package com.ekart.customerms.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ekart.customerms.entity.Customer;
import com.ekart.customerms.repository.CustomerRepository;
import com.ekart.customerms.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer createCustomer(Customer customer) {

		customerRepository.findByEmail(customer.getEmail()).ifPresent(existing -> {
			throw new RuntimeException("Customer already exists with email: " + customer.getEmail());
		});

		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> createCustomers(List<Customer> customers) {

		for (Customer customer : customers) {
			customerRepository.findByEmail(customer.getEmail()).ifPresent(existing -> {
				throw new RuntimeException("Customer already exists with email: " + customer.getEmail());
			});
		}

		return customerRepository.saveAll(customers);
	}

	@Override
	public Customer updateCustomer(Integer id, Customer customer) {

		Customer existingCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

		existingCustomer.setName(customer.getName());
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setMobile(customer.getMobile());
		existingCustomer.setCustomerNo(customer.getCustomerNo());

		return customerRepository.save(existingCustomer);
	}

	@Override
	public String deleteCustomer(Integer id) {

		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

		customerRepository.delete(customer);
		return "Customer deleted successfully";
	}

	@Override
	public Customer getCustomerById(Integer id) {

		return customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
}
