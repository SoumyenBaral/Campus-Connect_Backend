package com.campus.connect.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role;
import com.campus.connect.Repository.EventsRepository;
import com.campus.connect.Repository.UsersRepository;
import com.campus.connect.Service.UsersService;

@CrossOrigin(origins= "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class UsersController {


@Autowired
private UsersService usersService;

@Autowired
private UsersRepository usersRepository;

@Autowired
private EventsRepository eventsRepository;

@GetMapping("/counts")
public ResponseEntity<?> getCounts() {
    long hostCount = usersRepository.countByRole(Role.HOST);
    long coordinatorCount = usersRepository.countByRole(Role.COORDINATOR);
    long studentCount = usersRepository.countByRole(Role.STUDENT);
    long eventCount = eventsRepository.count();  // Count total events
    return ResponseEntity.ok(Map.of(
    		"hostCount", hostCount, 
    		"coordinatorCount", coordinatorCount,
    		"studentCount", studentCount,
    		"eventCount", eventCount
    		));
}

//Get unapproved hosts for admin review
@GetMapping("/unapproved-hosts")
public List<Users> getUnapprovedHosts() {
    return usersRepository.findByRoleAndIsApproved(Role.HOST, false);
}

//Admin approves/rejects a host
@PutMapping("/approve/{id}")
public ResponseEntity<String> approveHost(@PathVariable Long id, @RequestParam boolean approve) {
    Optional<Users> userOpt = usersRepository.findById(id);
    if (userOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }
    Users user = userOpt.get();
    if (user.getRole() != Role.HOST) {
        return ResponseEntity.badRequest().body("Only hosts can be approved.");
    }
    user.setApproved(approve);
    usersRepository.save(user);
    return ResponseEntity.ok(approve ? "Host approved." : "Host rejected.");
}

@PostMapping("/postuser")
private ResponseEntity<String>  addUser(@RequestBody Users user) {
	try {
		return ResponseEntity.ok(usersService.saveUser(user));
	}catch(IllegalArgumentException e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}

@GetMapping("/getuser")
public  List<Users> getAllUsers(){
	return usersService.getAllUsers();
}

@DeleteMapping("/deleteusers")
public String deleteAllUsers() {
    usersRepository.deleteAll();
    return "All users deleted successfully";
}



@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    Users user = usersService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

    if (user != null) {
        // SUCCESS: Returns User object (JSON)
        return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
        // FAILURE: Returns a structured JSON map for consistency
        Map<String, String> errorResponse = Collections.singletonMap("error", "Invalid email or password");
        
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED); // (401)
    }
}
}