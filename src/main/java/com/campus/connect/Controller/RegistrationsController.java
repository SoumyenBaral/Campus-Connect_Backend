package com.campus.connect.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Registrations;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.EventStatus;
import com.campus.connect.Entity.Enum.Role;
import com.campus.connect.Repository.EventsRepository;
import com.campus.connect.Repository.RegistrationsRepository;
import com.campus.connect.Repository.UsersRepository;
import com.campus.connect.Service.RegistrationsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api")
public class RegistrationsController {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationsController.class);
	
	@Autowired
	private RegistrationsService  registrationService;
	
	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RegistrationsRepository registrationsRepository;
	
	// Get available events for registration (upcoming or ongoing)
    @GetMapping("/available-events")
    public List<Events> getAvailableEvents() {
        return eventsRepository.findByStatusIn(List.of(EventStatus.UPCOMING, EventStatus.ONGOING));
    }

    @PostMapping(value="/postregistration",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createRegistration(@RequestBody  Registrations registration) {
		try {
			Long studentId = registration.getStudent().getId();
			 if (studentId == null) {
	                return ResponseEntity.badRequest().body("Student ID is required.");
	            }
			// Validate student exists and is a STUDENT
            Optional<Users> studentOpt = usersRepository.findById(registration.getStudent().getId());
            if (studentOpt.isEmpty() || studentOpt.get().getRole() != Role.STUDENT) {
            	logger.warn("Unauthorized registration attempt for user ID: {}", studentId);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only students are authorized to register for events.");
            }
            
            Users verifiedStudent = studentOpt.get();
            
         // 2. OVERWRITE registration details with the verified User's details
            registration.setFullName(verifiedStudent.getName());     // Get Name from Users entity
            registration.setEmailAddress(verifiedStudent.getEmail()); // Get Email from Users entity
            registration.setMobileNumber(verifiedStudent.getContact()); // Get Contact from Users entity (assuming it's a String that needs parsing)
            
            // 3. Set the managed Users entity back into the registration object
            registration.setStudent(verifiedStudent); 
            // ---------------
            
           
            String result = registrationService.CreateRegistration(registration);
            return ResponseEntity.ok(result);
        }catch (IllegalArgumentException e){
        	logger.error("Validation error during registration: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        
        }
		catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }		
	}
}