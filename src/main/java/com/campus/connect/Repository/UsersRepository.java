package com.campus.connect.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	// Custom finder method for checking if a user exists by email (email is UNIQUE in DB)
	Optional<Users> findByEmail(String email);
    
    // Custom finder method for checking if a user exists by name (optional)
//    Optional<Users> findByName(String name);

}
