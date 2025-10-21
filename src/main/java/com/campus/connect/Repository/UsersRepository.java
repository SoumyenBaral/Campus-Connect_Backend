package com.campus.connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
	

}
