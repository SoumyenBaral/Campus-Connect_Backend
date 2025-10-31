package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable; // 👈 NEW: For path variables (userId)
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;     // 👈 NEW: For host approval update
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;   // 👈 NEW: For query parameters (status)
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Service.UsersService;
import com.campus.connect.Exception.ResourceNotFoundException; // 👈 NEW: Required for exception handling

@CrossOrigin(origins= "http://localhost:4200/")
@RestController
@RequestMapping("/api") // Base URL changed to reflect /api
public class UsersController {

 

@Autowired
private UsersService usersService;

@PostMapping("/postuser")
private String addUser(@RequestBody Users user) {
	return usersService.saveUser(user);
}

@GetMapping("/getuser")
public  List<Users> getAllUsers(){
	return usersService.getAllUsers();
}


//@DeleteMapping("/deleteuser/{id}")
//public String deleteUser(@PathVariable Long id) {
//	return usersService.deleteUser(id);
//}

@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    // Assuming LoginRequest is defined elsewhere and imported.
    Users user = usersService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

    if (user != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
}

    // ===================================
    // NEW ADMIN API Endpoints
    // The path is defined as /api/admin/...
    // ===================================

    /**
     * Endpoint 1: Fetches aggregated data for Admin Dashboard reports.
     * URL: GET /api/admin/report
     */
    @GetMapping("/admin/report")
    public ResponseEntity<AdminReportDTO> getAdminReport() {
        // NOTE: Security check (is the current user ADMIN?) should be here or in service layer.
        AdminReportDTO report = usersService.getAdminReportData();
        return ResponseEntity.ok(report);
    }

    /**
     * Endpoint 2: Fetches a list of hosts awaiting approval.
     * URL: GET /api/admin/hosts/unapproved
     */
    @GetMapping("/admin/hosts/unapproved")
    public ResponseEntity<List<Users>> getUnapprovedHosts() {
        List<Users> unapprovedHosts = usersService.getUnapprovedHosts();
        return ResponseEntity.ok(unapprovedHosts);
    }

    /**
     * Endpoint 3: Updates the approval status of a Host application.
     * URL: PUT /api/admin/hosts/{userId}/approve?status=true
     */
    @PutMapping("/admin/hosts/{userId}/approve")
    public ResponseEntity<Users> updateHostApproval(@PathVariable Long userId, @RequestParam boolean status) {
        try {
            Users updatedUser = usersService.updateHostApprovalStatus(userId, status);
            return ResponseEntity.ok(updatedUser);
        } catch (ResourceNotFoundException e) {
            // User or pending application not found
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            // Logic error (e.g., user is already approved, or an invalid operation)
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            // General internal error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}