package com.campus.connect.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.campus.connect.Entity.Users;

public interface UsersService extends UserDetailsService {
	
//	Users validateUser = null;

	//Post
	String saveUser(Users user);
	
    
	//read
	    List<Users> getAllUsers();
	    
	//Deletes a user by their ID.
	    String deleteUser(Long id);

	    
	    
	    
	    
}
