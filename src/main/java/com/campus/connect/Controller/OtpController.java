package com.campus.connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.OtpRepository;
import com.campus.connect.Repository.UsersRepository;
import com.campus.connect.Service.OtpService;

@CrossOrigin(origins="http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class OtpController {

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UsersRepository  usersRepository;
	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestParam String email) {
		otpService.sendOtp(email);
	    return ResponseEntity.ok("OTP sent successfully");
	}

		
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(
			
	        @RequestParam String email,
	        @RequestParam String otp) {

	    // 6-digit validation (backend safety)
	    if (!otp.matches("\\d{6}")) {
	        return ResponseEntity.badRequest().body("OTP must be 6 digits");
	    }

	    boolean isValid = otpService.verifyOtp(email, otp);

	    if (!isValid) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body("Invalid or expired OTP");
	    }

	    return ResponseEntity.ok("OTP verified successfully");
	}
}
