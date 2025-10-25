package com.campus.connect.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired; // <-- You can remove this import if not used elsewhere
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    
    // Make fields final
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    // FIX: Inject ALL dependencies via constructor. 
    // This is required for Spring Boot 3+ best practices and circular dependency resolution.
    // The @Autowired is often optional here, but good for clarity.
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return usersRepository.findByEmail(email)
	    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
		
	}

	@Override
	public String saveUser(Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    usersRepository.save(user);
	    return "User registration success";
		
	}

	@Override
	public List<Users> getAllUsers() {
		return usersRepository.findAll();
	}

	@Override
	public String deleteUser(Long id) {
		if(usersRepository.existsById(id)) {
			usersRepository.deleteById(id);
			return "user data deleted successfully";
		}
		return "User not found for deletion.";
	}

	

    // REMOVE all original field-based @Autowired annotations from this class!

    // ... rest of the implementation methods (saveUser, loadUserByUsername, etc.)
}