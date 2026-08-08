package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import com.campus.connect.Entity.Users;

public interface UsersService{
	

	//Post
	String saveUser(Users user);
	
    
	//read
	List<Users> getAllUsers();
	    
	    
	//delete 
    String deleteAllUsers();
	        
	    
	    
	//For retrieving user by email
	Optional<Users> findByEmail(String email); 
		
	//Login logic - returns the user object if successful, null otherwise
	Users loginUser(String email, String password); 
		
	//Fogot password
	void forgotPassword(String email);
	//Reset password
	void resetPassword(String token, String newPassword);
	    
	    
}