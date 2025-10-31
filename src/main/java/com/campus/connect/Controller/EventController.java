package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.campus.connect.Entity.Events;
import com.campus.connect.Service.EventsService;
import com.campus.connect.Repository.UsersRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/postevent")
    public ResponseEntity<String> addEvent(@RequestBody Events events) {
        // Validate host ID
        if (events.getHost() == null || !usersRepository.existsById(events.getHost().getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid host ID");
        }

        // Optional: Set default status if not provided
        if (events.getStatus() == null) {
            events.setStatus(com.campus.connect.Entity.Enum.EventStatus.UPCOMING);
        }

        String result = eventsService.CreateEvent(events);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAllevents")
    public ResponseEntity<List<Events>> getEvents() {
        List<Events> allEvents = eventsService.getAllEvents();
        return ResponseEntity.ok(allEvents);
    }
}