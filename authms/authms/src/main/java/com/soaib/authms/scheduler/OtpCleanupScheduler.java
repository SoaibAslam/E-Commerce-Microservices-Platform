package com.soaib.authms.scheduler;

import com.soaib.authms.repository.OtpRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OtpCleanupScheduler {

	@Autowired
	private OtpRepository otpRepository;

	@Scheduled(fixedRate = 60000) // every 1 minute
	@Transactional // ✅ IMPORTANT FIX
	public void deleteExpiredOtps() {
		otpRepository.deleteByExpiryTimeBefore(LocalDateTime.now());
		System.out.println("Expired OTPs deleted");
	}
}