package com.soaib.authms.service;

import com.soaib.authms.entity.Otp;
import com.soaib.authms.entity.User;
import com.soaib.authms.repository.OtpRepository;
import com.soaib.authms.repository.UserRepository;
import com.soaib.authms.util.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ✅ REGISTER (no duplicate mobile)
	public String register(String mobile, String password) {

		Optional<User> existingUser = userRepository.findByMobile(mobile);

		if (existingUser.isPresent()) {
			User user = existingUser.get();

			// ❌ already active → block
			if ("ACTIVE".equals(user.getStatus())) {
				return "Mobile already registered. Please login.";
			}

			// 🔁 PENDING → resends OTP
			otpRepository.deleteByMobile(mobile);

		} else {
			// ✅ new user
			User user = new User();
			user.setMobile(mobile);
			user.setPassword(passwordEncoder.encode(password));
			user.setStatus("PENDING");

			try {
				userRepository.save(user);
			} catch (DataIntegrityViolationException e) {
				return "Mobile already registered";
			}
		}

		// ✅ generate OTP
		String otp = OtpUtil.generateOtp();

		Otp otpEntity = new Otp();
		otpEntity.setMobile(mobile);
		otpEntity.setOtp(otp);
		otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(2));

		otpRepository.save(otpEntity);

		System.out.println("OTP for " + mobile + " is: " + otp);

		return "OTP generated successfully";
	}

	// ✅ VERIFY OTP
	public String verifyOtp(String mobile, String otp) {

		Otp otpEntity = otpRepository.findTopByMobileOrderByIdDesc(mobile)
				.orElseThrow(() -> new RuntimeException("OTP not found"));

		// expired
		if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
			otpRepository.delete(otpEntity);
			return "OTP expired";
		}

		// invalid
		if (!otpEntity.getOtp().equals(otp)) {
			return "Invalid OTP";
		}

		// success → delete OTP
		otpRepository.delete(otpEntity);

		User user = userRepository.findByMobile(mobile).orElseThrow(() -> new RuntimeException("User not found"));

		user.setStatus("ACTIVE");
		userRepository.save(user);

		return "User verified successfully";
	}

	// ✅ LOGIN
	public String login(String mobile, String password) {

		User user = userRepository.findByMobile(mobile).orElseThrow(() -> new RuntimeException("User not found"));

		if (!user.getStatus().equals("ACTIVE")) {
			return "User not verified";
		}

		if (!passwordEncoder.matches(password, user.getPassword())) {
			return "Invalid credentials";
		}

		return "Login successful";
	}

	// ✅ CHECK USER EXISTS (extra API use)
	public boolean isUserRegistered(String mobile) {
		return userRepository.findByMobile(mobile).isPresent();
	}
}