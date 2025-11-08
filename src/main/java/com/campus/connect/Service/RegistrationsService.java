package com.campus.connect.Service;

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Registrations;
import com.campus.connect.Entity.Users;

import java.util.Optional;
import java.util.List;

public interface RegistrationsService {

	
	//post
	String CreateRegistration(Registrations registration) throws IllegalArgumentException; 
	
	
	
	
	//get
//	   Optional<Registrations> getRegistrationById(Long id);
	   
	   
	   //get all student
//	   List<Registrations> getAllRegistrations();
   
	   
//    /** Delete a registration by its ID. */
//    void deleteRegistration(Long id);
	
	
//    /** * Checks if a student is already registered for an event. 
//     * @param student The User entity.
//     * @param event The Event entity.
//     */
//    boolean isStudentRegisteredForEvent(Users student,Events event);


}
