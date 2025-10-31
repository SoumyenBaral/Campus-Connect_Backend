package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Host;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role; // 👈 Required Import for Roles
import com.campus.connect.Repository.HostRepository;
import com.campus.connect.Repository.UsersRepository;
import com.campus.connect.Repository.EventsRepository; // 👈 Required Import for Events Count
import com.campus.connect.Controller.AdminReportDTO; // 👈 Required Import for Reporting DTO
// You may need to create these custom exceptions if they don't exist:
import com.campus.connect.Exception.ResourceNotFoundException; 

@Service
public class UsersServiceImpl implements UsersService {
    
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private HostRepository hostRepository; // 👈 NEW Injection for Host Management
    
    @Autowired
    private EventsRepository eventsRepository; // 👈 NEW Injection for Reporting
    

    // --- Existing Functionality ---
    
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

    @Override
    public Optional<Users> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

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
    
    // --- NEW ADMIN FUNCTIONALITIES ---
    
    /**
     * 1. Admin Reporting Implementation
     */
    @Override
    public AdminReportDTO getAdminReportData() {
        // 1. Total Events (provided by JpaRepository.count())
        long totalEvents = eventsRepository.count(); 

        // 2. Counts by Role (provided by UsersRepository.countByRole())
        long totalStudents = usersRepository.countByRole(Role.STUDENT);
        long totalHosts = usersRepository.countByRole(Role.HOST);
        long totalCoordinators = usersRepository.countByRole(Role.COORDINATOR);
        
        return new AdminReportDTO(totalEvents, totalHosts, totalCoordinators, totalStudents);
    }
    
    /**
     * 2. Get Unapproved Hosts Implementation
     */
    @Override
    public List<Users> getUnapprovedHosts() {
        // Fetch Host entities with "PENDING" status from the hosts_pending_approval table
        List<Host> pendingHosts = hostRepository.findByApprovalStatus("PENDING");
        
        // Map Host entities back to Users entities for the frontend list
        return pendingHosts.stream()
                .map(Host::getUser) // Uses the 'user' field in the Host entity
                .toList();
    }
    
    /**
     * 3. Update Host Approval Status Implementation
     */
    @Override
    public Users updateHostApprovalStatus(Long userId, boolean status) {
        // 1. Find the User
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));

        // 2. Find the pending Host application entry
        Host pendingHost = hostRepository.findByUser(user);
        if (pendingHost == null) {
             if (user.getRole() == Role.HOST) {
                 throw new IllegalArgumentException("User ID " + userId + " is already an approved HOST.");
            }
            throw new ResourceNotFoundException("No pending HOST application found for User ID: " + userId);
        }
        
        if (status) { // Host ACCEPTED (status = true)
            // 3. Update the User's role to HOST in the Users table
            user.setRole(Role.HOST);
            usersRepository.save(user);
            
            // 4. Remove the entry from the hosts_pending_approval table
            hostRepository.delete(pendingHost); 
            
        } else { // Host REJECTED (status = false)
            // 3. Keep User's role as STUDENT/default and remove the application
            hostRepository.delete(pendingHost);
        }
        
        return user;
    }
}