package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.EventStatus;
import com.campus.connect.Entity.Enum.Role; 
import com.campus.connect.Repository.EventsRepository;
import com.campus.connect.Repository.UsersRepository;

@Service
public class EventsServiceImpl implements EventsService { 

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private UsersRepository usersRepository;

	@Override
    @Transactional
	public String CreateEvent(Events eventDetails) {
        
        // 1. Validate host ID in payload
		if (eventDetails.getTitle() == null || eventDetails.getTitle().trim().isEmpty()) {
            return "Error: Event title is required.";
        }
        if (eventDetails.getLocation() == null || eventDetails.getLocation().trim().isEmpty()) {
            return "Error: Event location is required.";
        }
        if (eventDetails.getEventDate() == null) {
            return "Error: Event date is required.";
        }
        if (eventDetails.getHost() == null || eventDetails.getHost().getId() == null) {
            return "Error: Host ID is missing.";
        }
        
        Long hostId = eventDetails.getHost().getId();
        Optional<Users> hostOptional = usersRepository.findById(hostId);
        
        if (hostOptional.isEmpty()) {
            return "Error: Host user not found for ID: " + hostId;
        }
        
        Users host = hostOptional.get();
        
        // 2. CRITICAL SECURITY/BUSINESS LOGIC CHECK: Ensure user is authorized to host
        if (host.getRole() != Role.HOST) {
            return "Error: Only users with HOST role can create events.";
        }
        if (!host.isApproved()) {
            return "Error: Host is not approved by admin. Contact admin for approval.";
        }
        
        // 3. Set the managed host entity
        eventDetails.setHost(host); 
        
        // 4. Set the initial status 
        eventDetails.setStatus(EventStatus.UPCOMING); 
        
        // 5. Save to the database
        eventsRepository.save(eventDetails);
        
        return "Event created successfully!";
	}

	@Override
	public List<Events> getAllEvents() {
		return eventsRepository.findAll();
	}
}
