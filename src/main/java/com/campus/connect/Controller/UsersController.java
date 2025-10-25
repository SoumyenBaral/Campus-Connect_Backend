package com.campus.connect.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Security.JwtService;
import com.campus.connect.Service.UsersService;

@CrossOrigin(origins= "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class UsersController {

private final  UsersService usersService;
private  final AuthenticationManager authenticationManager;
private  final JwtService jwtService;
private final  UserDetailsService userDetailsService;
 

public UsersController(
		
		 @Lazy UsersService usersService,
		 AuthenticationManager authenticationManager,
		 JwtService jwtService,
		 UserDetailsService userDetailsService) {
	
	this.usersService = usersService;
    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
}



@PostMapping("/postuser")
private String addUser(@RequestBody Users user) {
	return usersService.saveUser(user);
}

@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    try {
        // 1. Authenticate credentials using the AuthenticationManager
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password)
        );
     // ...
        final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        // Pass the userDetails (the user) to the now-functional JwtService
        final String jwt = jwtService.generateToken(userDetails); // <-- Ensure this is calling the implemented method
        // ...
     // 4. Return the JWT and role
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        // Assuming Users implements UserDetails, get the role from the granted authority
        response.put("role", userDetails.getAuthorities().iterator().next().getAuthority());
        
        return ResponseEntity.ok(response);

    } catch (Exception e) {
        // Authentication failed (Invalid email or password)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
    }
}


@GetMapping("/getuser")
public  List<Users> getAllUsers(){
	return usersService.getAllUsers();
}
@DeleteMapping("/deleteuser/{id}")
public String deleteUser(@PathVariable Long id) {
	return usersService.deleteUser(id);
}


}
