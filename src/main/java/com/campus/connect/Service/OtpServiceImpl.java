package com.campus.connect.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Otp;
import com.campus.connect.Repository.OtpRepository;


@Service
public class OtpServiceImpl  implements OtpService{

	@Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender mailSender;

    // OTP validity in minutes
    private static final int OTP_EXPIRY_MINUTES = 2;

    @Override
    public void sendOtp(String email) {

        // Invalidate previous OTPs (optional but recommended)
        otpRepository.findTopByEmailOrderByIdDesc(email)
                .ifPresent(oldOtp -> {
                    oldOtp.setUsed(true);
                    otpRepository.save(oldOtp);
                });

        // Generate 6-digit OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        otpEntity.setUsed(false);

        otpRepository.save(otpEntity);

        // Send OTP via Email
        sendOtpEmail(email, otp);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {

        Optional<Otp> otpOptional = otpRepository.findTopByEmailOrderByIdDesc(email);

        if (otpOptional.isEmpty()) {
            return false;
        }

        Otp otpEntity = otpOptional.get();

        // Check if already used
        if (otpEntity.isUsed()) {
            return false;
        }

        // Check expiry
        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            return false;
        }

        // Check OTP match
        if (!otpEntity.getOtp().equals(otp)) {
            return false;
        }

        // Mark OTP as used
        otpEntity.setUsed(true);
        otpRepository.save(otpEntity);

        return true;
    }

    private void sendOtpEmail(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("CampusConnect - Email Verification OTP");
        message.setText(
                "Your OTP for email verification is: " + otp +
                "\n\nThis OTP is valid for 2 minutes." +
                "\n\nDo not share this OTP with anyone."
        );

        mailSender.send(message);
    }
	
}
