package com.campus.connect.Service;

import java.time.LocalDateTime;
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
        
     // --- NEW VALIDATION CHECK ---
        LocalDateTime now = LocalDateTime.now();
        if (eventDetails.getEventDate().isBefore(now.plusMinutes(5))) { // Adding a small buffer (e.g., 5 mins)
            return "Error: Event date must be in the future. Please schedule at least 5 minutes from now.";
        }
        // --- END NEW VALIDATION CHECK ---
        
        
        // 3. Set the managed host entity
        eventDetails.setHost(host); 
        
        // 4. Set the initial status 
        eventDetails.setStatus(EventStatus.UPCOMING); 
        
        
//        LocalDateTime now = LocalDateTime.now();
//        if (eventDetails.getEventDate().isBefore(now.minusMinutes(1))) {
//            eventDetails.setStatus(EventStatus.COMPLETED);  // Set to COMPLETED if past
//            System.out.println("Event created with past date - status set to COMPLETED: " + eventDetails.getTitle());
//        } else if (eventDetails.getEventDate().isBefore(now.plusHours(1))) {
//            eventDetails.setStatus(EventStatus.ONGOING);  // If within 1 hour, set to ONGOING
//        }
        
        // 5. Save to the database
        eventsRepository.save(eventDetails);
        
        return "Event created successfully!";
	}

	@Override
    public List<Events> getAllEvents() {
        // Update statuses before fetching (for dynamic updates)
        updateEventStatuses();
        // Filter to only UPCOMING and ONGOING
        return eventsRepository.findByStatusIn(List.of(EventStatus.UPCOMING, EventStatus.ONGOING));
    }
	
	
    @Override
    public Optional<Events> getEventById(Long id) {
        return eventsRepository.findById(id);
    }
	
    // New method: Update event statuses based on current time
    @Transactional
    public void updateEventStatuses() {
        List<Events> allEvents = eventsRepository.findAll();  // Get all to check statuses
        LocalDateTime now = LocalDateTime.now();
        boolean updated  = false;
        for (Events event : allEvents) {
            if (event.getEventDate().isBefore(now.minusMinutes(1)) && event.getStatus() != EventStatus.COMPLETED) {
                event.setStatus(EventStatus.COMPLETED);
                updated = true;
            }else if (event.getEventDate().isBefore(now.plusHours(1)) && event.getEventDate().isAfter(now.minusMinutes(1)) && event.getStatus() != EventStatus.ONGOING) {
                event.setStatus(EventStatus.ONGOING);
                updated = true;
            }
            
            }
            // UPCOMING remains if not matching above
        if (updated) {
            eventsRepository.saveAll(allEvents);
        }
        
    }
    
 // NEW: Helper to update a single event's status
//    private void updateSingleEventStatus(Events event) {
//        LocalDateTime now = LocalDateTime.now();
//        if (event.getEventDate().isBefore(now.minusMinutes(1)) && event.getStatus() != EventStatus.COMPLETED) {
//            event.setStatus(EventStatus.COMPLETED);
//            System.out.println("Single event updated to COMPLETED: " + event.getTitle());
//        } else if (event.getEventDate().isBefore(now.plusHours(1)) && event.getEventDate().isAfter(now.minusMinutes(1)) && event.getStatus() != EventStatus.ONGOING) {
//            event.setStatus(EventStatus.ONGOING);
//        }
//    }

	@Override
	public long getTotalEventCount() {
		return eventsRepository.count();
	}  
    
}
