package com.campus.connect.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.campus.connect.Entity.Users;

public interface UsersService {
	
	Users validateUser = null;

	//Post
	String saveUser(Users user);
	
	
	// ... existing methods for login ...
	    Users getUserByEmail(String email); // Add this for login lookup
	    
	    Users validateUser(String email, String password);
		

	    
	  //read
	    List<Users> getAllUsers();
	    

	    /** Deletes a user by their ID. */
	    String deleteUser(Long id);


		UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

	    
	    
	/** Retrieves a user by their ID. */
//    List<Users> getUserById(Long id);



    
    /** Updates an existing user's details. */
//    Users updateUser(Long id, Users userDetails);

    /** Retrieves a user by email (for login/check). */
//    List<Users> getUserByEmail(String email);
}

