package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Users;
import com.campus.connect.Service.UsersService;

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
	

}
