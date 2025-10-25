package com.campus.connect.Security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;

public class JwtService {
	@Value("${application.security.jwt.secret-key}")
    private String secretKey;

	public String generateToken(UserDetailsService userDetailsService) {
		// TODO Auto-generated method stub
		return null;
	}
}
