package com.campus.connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;
@Service
public class UsersServiceImpl implements UsersService {
@Autowired
	private UsersRepository usersRepository;


@Autowired 
private PasswordEncoder passwordEncoder;


//post
	@Override
	public String saveUser(Users user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		usersRepository.save(user);
		return "User registration success";
	}
	
	
	//get all user
	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}
	
	//delete
	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		if(usersRepository.existsById(id)) {
			usersRepository.deleteById(id);
			return "user data deleted successfully";
		}
		else {
			return "user not found ";
		}
	}

	@Override
	public Users getUserByEmail(String email) {
	    // Use findByEmail and handle the Optional if necessary
	    return usersRepository.findByEmail(email).orElse(null);
	}

	
	@Override
	public Users validateUser(String email, String password) {
		Users user = getUserByEmail(email);
	    
		if(user != null) {
			if (user.getPassword() != null && user.getPassword().equals(password)) {
		        return user; //success
		    }
		}
	   
	    return null;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
