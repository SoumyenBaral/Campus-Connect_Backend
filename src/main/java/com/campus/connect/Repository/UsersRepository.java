package com.campus.connect.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	// NEW: Method to find a user by their email (used as username)
		Optional<Users> findByEmail(String email);
}
