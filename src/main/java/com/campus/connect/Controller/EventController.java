package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Events;
import com.campus.connect.Service.EventsService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventController {

	@Autowired
	private EventsService eventsService;
	
	
	@PostMapping("/postevent")
	private String addEvent(@RequestBody Events events) {
	return	eventsService.CreateEvent(events);
	}
	
	@GetMapping("/getAllevents")
	private List<Events>getEvents(){
		return eventsService.getAllEvents();
	}
}
