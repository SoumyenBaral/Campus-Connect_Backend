package com.campus.connect.Service;

import java.util.List;


import com.campus.connect.Entity.Users;

public interface UsersService{
	
//	Users validateUser = null;

	//Post
	String saveUser(Users user);
	
    
	//read
	    List<Users> getAllUsers();
	    
	//Deletes a user by their ID.
	    String deleteUser(Long id);

	    
	    
	    
	    
}
