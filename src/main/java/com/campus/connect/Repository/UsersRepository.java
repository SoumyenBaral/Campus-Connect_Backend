package com.campus.connect.Repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role;

public interface UsersRepository extends JpaRepository<Users, Long> {
	
	// NEW: Method to find a user by their email (used as username)
		Optional<Users> findByEmail(String email);
		
		//for admin dashboard:count hosts
		long countByRole(Role role);
		
		 // For admin approval: Get unapproved hosts
	    List<Users> findByRoleAndIsApproved(Role role, boolean isApproved);
		
		
}
