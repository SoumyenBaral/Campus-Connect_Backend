package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import com.campus.connect.Entity.Users;
import com.campus.connect.Controller.AdminReportDTO; // 👈 Import for the reporting data structure

public interface UsersService {
	
	//Post
	String saveUser(Users user);
	
    
	//read
	    List<Users> getAllUsers();
	    
	//Deletes a user by their ID.
//	    String deleteUser(Long id);  
	    
	 // NEW: For retrieving user by email
		Optional<Users> findByEmail(String email); 
		
		// NEW: Login logic - returns the user object if successful, null otherwise
		Users loginUser(String email, String password); 
	    
	    
		// ===================================
		// NEW ADMIN FUNCTIONALITIES
		// ===================================
		
		/**
		 * Retrieves aggregated data for the admin dashboard report.
		 * Includes counts of total events, hosts, coordinators, and students.
		 * @return AdminReportDTO
		 */
		AdminReportDTO getAdminReportData();
		
		/**
		 * Retrieves a list of users who have applied for the HOST role 
		 * and are currently awaiting approval (PENDING).
		 * @return List of Users
		 */
		List<Users> getUnapprovedHosts();
		
		/**
		 * Updates the approval status for a host application.
		 * If status is true, the user's role is updated to HOST.
		 * @param userId The ID of the user (applicant).
		 * @param status true for accept, false for reject.
		 * @return The updated Users object.
		 */
		Users updateHostApprovalStatus(Long userId, boolean status);
	    
}