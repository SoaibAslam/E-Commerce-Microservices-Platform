package com.ekart.paymentms.repository;

import com.ekart.paymentms.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}