package com.campus.connect.Service;

public interface OtpService {

	void sendOtp(String email);
	

    boolean verifyOtp(String email, String otp);


	void sendWelcomeEmail(String email, String name);
	
}
