package com.ekart.customerms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekart.customerms.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Optional<Customer> findByEmail(String email);

}