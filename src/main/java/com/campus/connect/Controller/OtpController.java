package com.campus.connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.campus.connect.Service.OtpService;


@RestController
@RequestMapping("/api")
public class OtpController {

	@Autowired
	private OtpService otpService;
	
	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestParam String email) {
		otpService.sendOtp(email);
	    return ResponseEntity.ok("OTP sent successfully");
	}

	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(
	        @RequestParam String email,
	        @RequestParam String otp) {

	    otpService.verifyOtp(email, otp);
	    return ResponseEntity.ok("OTP verified successfully");
	}
}