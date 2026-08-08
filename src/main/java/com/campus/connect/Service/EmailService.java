package com.campus.connect.Service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService  {
	
    void sendEmail(String to, String subject, String message);
    
}
