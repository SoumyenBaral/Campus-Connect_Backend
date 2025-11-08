package com.campus.connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Registrations;
import com.campus.connect.Entity.Enum.EventStatus;
import com.campus.connect.Repository.EventsRepository;
import com.campus.connect.Repository.RegistrationsRepository;
import com.campus.connect.Repository.UsersRepository;


@Service
public class RegistrationsServiceImpl  implements RegistrationsService{

	private static final Logger logger = LoggerFactory.getLogger(RegistrationsServiceImpl.class);
	
@Autowired
private RegistrationsRepository registrationsRepository;

@Autowired
private EventsRepository eventsRepository;
	
@Autowired
private UsersRepository usersRepository;

	

	@Override
	public String CreateRegistration(Registrations registration) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Events event = eventsRepository.findById(registration.getEvent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Event not found."));
        if (event.getStatus() == EventStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot register for completed events.");
        }
        // Check for duplicate registration
        if (registrationsRepository.findByStudentAndEvent(registration.getStudent(), event).isPresent()) {
            throw new IllegalArgumentException("Already registered for this event.");
        }
        // Save the registration
        registrationsRepository.save(registration);
        logger.info("Registration successful for student ID: {} and event ID: {}", 
                    registration.getStudent().getId(), event.getId());
        return "Registration successful!";
	}
}
