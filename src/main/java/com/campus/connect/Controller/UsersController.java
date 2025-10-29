package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
public  List<Users> getAllUsers(){
	return usersService.getAllUsers();
}


//@DeleteMapping("/deleteuser/{id}")
//public String deleteUser(@PathVariable Long id) {
//	return usersService.deleteUser(id);
//}
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    Users user = usersService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

    if (user != null) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
}

}
