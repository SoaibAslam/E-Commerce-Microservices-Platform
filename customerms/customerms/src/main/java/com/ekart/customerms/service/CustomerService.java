package com.ekart.customerms.service;

import java.util.List;

import com.ekart.customerms.entity.Customer;

public interface CustomerService {
	Customer createCustomer(Customer customer);

	List<Customer> createCustomers(List<Customer> customers);

	Customer updateCustomer(Integer id, Customer customer);

	String deleteCustomer(Integer id);

	Customer getCustomerById(Integer id);

	List<Customer> getAllCustomers();
}
