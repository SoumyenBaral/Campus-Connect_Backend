package com.campus.connect.Service;

import java.util.List;

import com.campus.connect.Entity.Events;

public interface EventsService {

	
	String CreateEvent(Events events);
	
	List<Events> getAllEvents();
	
}
