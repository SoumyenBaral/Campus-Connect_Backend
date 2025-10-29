package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    
@Autowired
    private UsersRepository usersRepository;

@Override
public String saveUser(Users user) {
	// TODO Auto-generated method stub
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
        
        if (user.getPassword().equals(password)) {
            return user; // Login successful
        }
    }
    return null; // User not found or password incorrect
}

}