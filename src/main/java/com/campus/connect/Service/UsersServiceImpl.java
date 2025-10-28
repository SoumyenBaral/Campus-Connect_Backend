package com.campus.connect.Service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
    
@Autowired
    private UsersRepository usersRepository;

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

//@Override
//public String deleteUser(Long id) {
//	// TODO Auto-generated method stub
//	return null;
//}


}