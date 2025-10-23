package com.campus.connect.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Service.UsersService;

@CrossOrigin(origins= "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class UsersController {
	
@Autowired 
private UsersService usersService;
 
@PostMapping("/postuser")
private String addUser(@RequestBody Users user) {
	return usersService.saveUser(user);
}
@GetMapping("/getuser")
private List<Users> getAllUsers(){
	return usersService.getAllUsers();
}
@DeleteMapping("/deleteuser/{id}")
public String deleteUser(@PathVariable Long id) {
	return usersService.deleteUser(id);
}
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    Users user = usersService.validateUser(email, password); 

    if (user != null) {
        // Success: Return the role name as a simple string
        return ResponseEntity.ok(user.getRole().toString());
    } else {
        // Failure
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

}
