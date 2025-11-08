package com.campus.connect.Controller;

   import java.time.LocalDateTime;
   import java.util.List;
   import java.util.Optional;

   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.CrossOrigin;
   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.PostMapping;
   import org.springframework.web.bind.annotation.RequestBody;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;

   import com.campus.connect.Entity.Events;
   import com.campus.connect.Entity.Registrations;
   import com.campus.connect.Entity.Users;
   import com.campus.connect.Entity.Enum.EventStatus;
   import com.campus.connect.Service.EventsService;
   import com.campus.connect.Repository.RegistrationsRepository;
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
   	public ResponseEntity<String> addEvent(@RequestBody Events eventDetails) {
           try {
               String result = eventsService.CreateEvent(eventDetails);
               
               if (result.startsWith("Error:")) {
                   return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
               }
               
               return new ResponseEntity<>(result, HttpStatus.OK);  // 201 for successful creation
           } catch (Exception e) {
               return new ResponseEntity<>("Error: Internal server error - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
       
   	@GetMapping(value="/getAllevents",produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<List<Events>> getEvents() {
           try {
               List<Events> events = eventsService.getAllEvents();
               return new ResponseEntity<>(events, HttpStatus.OK);
           } catch (Exception e) {
               return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
   	@GetMapping("/admin/event/total-count")
   	public ResponseEntity<Long> getTotalEventsCount() {
   	    try {
   	        // You might add authorization logic here later (e.g., only Admin role can access)
   	        long count = eventsService.getTotalEventCount();
   	        return new ResponseEntity<>(count, HttpStatus.OK);
   	    } catch (Exception e) {
   	        return new ResponseEntity<>(0L, HttpStatus.INTERNAL_SERVER_ERROR); 
   	    }
   	}
   	
   
       // Get a single event by ID (for preselecting in registration form)
       @GetMapping("/events/{id}")
       public ResponseEntity<Events> getEventById(@PathVariable Long id) {
           try {
               Optional<Events> event = eventsService.getEventById(id);
               if (event.isPresent()) {
                   return new ResponseEntity<>(event.get(), HttpStatus.OK);
               } else {
                   return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
               }
           } catch (Exception e) {
               return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
   }

   