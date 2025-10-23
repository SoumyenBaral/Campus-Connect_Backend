package com.campus.connect.Repository;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Registrations;
import com.campus.connect.Entity.Users;

public interface RegistrationsRepository extends JpaRepository<Registrations, Long>{

	
	 // Find all registrations for a specific student (User)
    List<Registrations> findByStudent(User student);

	
 // Find a registration by a specific student and event combination (for checking duplicates)
    Optional<Registrations> findByStudentAndEvent(Users student,Events events);

}
 