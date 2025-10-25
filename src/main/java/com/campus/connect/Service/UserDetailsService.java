package com.campus.connect.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UserDetailsService implements UsersService{

	private  UsersRepository usersRepository;

    public UserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the Users entity by email and throw an exception if not found
        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
	@Override
	public String saveUser(Users user) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Users getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Users validateUser(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String deleteUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
