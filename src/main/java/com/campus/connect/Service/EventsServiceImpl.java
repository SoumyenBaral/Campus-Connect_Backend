package com.campus.connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Events;
import com.campus.connect.Repository.EventsRepository;

@Service
public class EventsServiceImpl implements EventsService{

	@Autowired
	private EventsRepository eventsRepository;

	@Override
	public String CreateEvent(Events events) {
		// TODO Auto-generated method stub
		
		eventsRepository.save(events);
		return "Event Creation success";
	}

	@Override
	public List<Events> getAllEvents() {
		// TODO Auto-generated method stub
		
		return eventsRepository.findAll();
	}
	
	
	
}
