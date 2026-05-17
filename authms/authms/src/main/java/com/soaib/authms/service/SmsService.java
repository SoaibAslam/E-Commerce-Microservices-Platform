package com.soaib.authms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

	public void sendOtp(String mobile, String otp) {

		// 🔴 Replace with your real API key
		String apiKey = "YOUR_API_KEY";

		String url = "https://www.fast2sms.com/dev/bulkV2?" + "authorization=" + apiKey + "&route=otp"
				+ "&variables_values=" + otp + "&numbers=" + mobile;

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject(url, String.class);
	}
}