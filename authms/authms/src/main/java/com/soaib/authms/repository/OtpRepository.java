package com.soaib.authms.repository;

import com.soaib.authms.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {

	// ✅ Get latest OTP
	Optional<Otp> findTopByMobileOrderByIdDesc(String mobile);

	// ✅ Delete expired OTPs
	@Transactional
	int deleteByExpiryTimeBefore(LocalDateTime now);

	// ✅ Delete OTP by mobile (after verification)
	@Transactional
	int deleteByMobile(String mobile);
}