package com.campus.connect.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
public class RegistrationsController {

	
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

	
	@PostMapping("/postregistration")
	public ResponseEntity<String> createRegistration(@RequestBody  Registrations registration) {
		try {
            // Validate student exists and is a STUDENT
            Optional<Users> studentOpt = usersRepository.findById(registration.getStudent().getId());
            if (studentOpt.isEmpty() || studentOpt.get().getRole() != Role.STUDENT) {
                return ResponseEntity.badRequest().body("Invalid student.");
            }
            // Validate event exists and is available for registration
            Optional<Events> eventOpt = eventsRepository.findById(registration.getEvent().getId());
            if (eventOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Event not found.");
            }
            Events event = eventOpt.get();
            if (event.getStatus() == EventStatus.COMPLETED) {
                return ResponseEntity.badRequest().body("Cannot register for completed events.");
            }
            // Check for duplicate registration
            Optional<Registrations> existing = registrationsRepository.findByStudentAndEvent(studentOpt.get(), event);
            if (existing.isPresent()) {
                return ResponseEntity.badRequest().body("Already registered for this event.");
            }
            // Proceed with registration
            String result = registrationService.CreateRegistration(registration);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed: " + e.getMessage());
        }		
	}
	
}
