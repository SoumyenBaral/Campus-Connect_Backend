package com.campus.connect.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    
@Autowired
    private UsersRepository usersRepository;

@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
private EmailService emailService;

private static final Pattern CONTACT_PATTERN = Pattern.compile("^\\d{10}$");


@Override
public String saveUser(Users user) {
	// Validate contact format
    if (user.getContact() == null || !CONTACT_PATTERN.matcher(user.getContact()).matches()) {
        throw new IllegalArgumentException("Contact number must be exactly 10 digits (numbers only).");
    }
    // Check for single admin (see next section)
    if (user.getRole() != null && user.getRole().name().equals("ADMIN")) {
        long adminCount = usersRepository.countByRole(com.campus.connect.Entity.Enum.Role.ADMIN);
        if (adminCount > 0) {
            throw new IllegalArgumentException("Only one admin is allowed.");
        }
    }
    // Encode password here
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    usersRepository.save(user);

    return "created success";
}

@Override
public List<Users> getAllUsers() {
	// TODO Auto-generated method stub
	return usersRepository.findAll();
}


//NEW: findByEmail implementation
@Override
public Optional<Users> findByEmail(String email) {
    return usersRepository.findByEmail(email);
}

// NEW: Login implementation
@Override
public Users loginUser(String email, String password) {

    Optional<Users> userOptional = usersRepository.findByEmail(email);

    if (userOptional.isPresent()) {
        Users user = userOptional.get();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
    }

    return null;
}



//@Override
//public Users loginUser(String email, String password) {
//    Optional<Users> userOptional = usersRepository.findByEmail(email);
//
//    if (userOptional.isPresent()) {
//        Users user = userOptional.get();
//        
////        if (user.getPassword().equals(password)) {
////            return user; // Login successful
////        }
//        if (passwordEncoder.matches(password, user.getPassword())) {
//        }
//    }
//    return null; // User not found or password incorrect
//}

@Override
@Transactional
public String deleteAllUsers() {
	usersRepository.deleteAll();
	return "All users deleted successfully";
}

@Override
public void forgotPassword(String email) {
	 Optional<Users> optionalUser = usersRepository.findByEmail(email);

	    if (optionalUser.isEmpty()) {
	        return; // Don't reveal if email exists
	    }

	    Users user = optionalUser.get();

	    String token = UUID.randomUUID().toString();
	    user.setResetToken(token);
	    user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

	    usersRepository.save(user);

	    String resetLink = "http://localhost:4200/reset-password?token=" + token;

	    emailService.sendEmail(
	    	    user.getEmail(),
	    	    "Password Reset",
	    	    "Click here to reset your password: " + resetLink
	    	);
}

@Override
public void resetPassword(String token, String newPassword) {
	  Optional<Users> optionalUser = usersRepository.findByResetToken(token);

	    if (optionalUser.isEmpty()) {
	        throw new RuntimeException("Invalid token");
	    }

	    Users user = optionalUser.get();

	    if (user.getTokenExpiry().isBefore(LocalDateTime.now())) {
	        throw new RuntimeException("Token expired");
	    }

	    user.setPassword(passwordEncoder.encode(newPassword));
	    user.setResetToken(null);
	    user.setTokenExpiry(null);

	    usersRepository.save(user);
}

	
}