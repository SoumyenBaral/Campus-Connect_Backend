package com.campus.connect.Service;

import java.util.List;

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
		return "User registration success";
	}
	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}
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
	
	// Assuming you have a method in your repository to find by email (e.g., in UsersRepository)
	// public interface UsersRepository extends JpaRepository<Users, Long> {
//	     Optional<Users> findByEmail(String email);
	// }

	@Override
	public Users getUserByEmail(String email) {
	    // Use findByEmail and handle the Optional if necessary
	    return usersRepository.findByEmail(email).orElse(null);
	}

	// NOTE: Password validation (e.g., using BCrypt) should happen here
	// For simplicity without DTOs:
	
	public Users validateUser(String email, String password) {
		Users user = getUserByEmail(email);
		List<Users> userList= findUserByEmail(email);
	    
	    // BASIC check (MUST be replaced by secure password hashing/checking)
	    if (user.getPassword() != null && user.getPassword().equals(password)) {
	        return user;
	    }
	    return null;
	}
	@Override
	public List<Users> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
