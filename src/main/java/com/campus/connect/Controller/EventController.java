package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> addEvent(@RequestBody Events eventDetails) {
        try {
            String result = eventsService.CreateEvent(eventDetails);
            
            if (result.startsWith("Error:")) {
                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
            }
            
            return new ResponseEntity<>(result, HttpStatus.CREATED);  // 201 for successful creation
        } catch (Exception e) {
            return new ResponseEntity<>("Error: Internal server error - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	@GetMapping("/getAllevents")
	public ResponseEntity<List<Events>> getEvents() {
        try {
            List<Events> events = eventsService.getAllEvents();
            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
