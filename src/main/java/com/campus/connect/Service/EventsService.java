package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import com.campus.connect.Entity.Events;

public interface EventsService {

	
	String CreateEvent(Events events);
	
	List<Events> getAllEvents();
	
	Optional<Events> getEventById(Long id);
	
	long getTotalEventCount();
	
	
	
}
