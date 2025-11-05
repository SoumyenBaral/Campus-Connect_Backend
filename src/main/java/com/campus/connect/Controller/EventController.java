package com.campus.connect.Controller;

   import java.time.LocalDateTime;
   import java.util.List;
   import java.util.Optional;

   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.http.HttpStatus;
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
       private RegistrationsRepository registrationsRepository;

       @Autowired
       private UsersRepository usersRepository;

       // ... (postevent and getAllevents remain unchanged)
       
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

       // Register a user for an event (updated to use full details)
       @PostMapping("/register")
       public ResponseEntity<String> registerForEvent(@RequestBody Registrations request) {
           try {
               // Validate required inputs
               if (request.getStudent() == null || request.getStudent().getId() == null ||
            		   request.getEvent() == null || request.getEvent().getId() == null ||
                   request.getFullName().trim().isEmpty() ||
                   request.getEmailAddress().trim().isEmpty() ){
                   return new ResponseEntity<>("Error: User ID, Event ID, Full Name, and Email are required.", HttpStatus.BAD_REQUEST);
               }

               // Check if user exists
               Long studentId = request.getStudent().getId();
               Optional<Users> userOpt = usersRepository.findById(studentId); 
               if (userOpt.isEmpty()) {
                   return new ResponseEntity<>("Error: User not found.", HttpStatus.BAD_REQUEST);
               }
               Users user = userOpt.get();

               // Check if event exists and is active
               Long eventId = request.getEvent().getId();
               Optional<Events> eventOpt = eventsService.getEventById(eventId);
               if (eventOpt.isEmpty()) {
                   return new ResponseEntity<>("Error: Event not found.", HttpStatus.BAD_REQUEST);
               }
               Events event = eventOpt.get();
               if (event.getStatus() == EventStatus.COMPLETED) {
                   return new ResponseEntity<>("Error: Cannot register for a completed event.", HttpStatus.BAD_REQUEST);
               }

               // Check if already registered (using your repository method)
               Optional<Registrations> existingReg = registrationsRepository.findByStudentAndEvent(user, event);
               if (existingReg.isPresent()) {
                   return new ResponseEntity<>("Error: User already registered for this event.", HttpStatus.BAD_REQUEST);
               }

               // Create and save registration with full details
               Registrations registration = new Registrations();
               registration.setFullName(request.getFullName());
               registration.setEmailAddress(request.getEmailAddress());
               registration.setMobileNumber(request.getMobileNumber());
               registration.setOrganization(request.getOrganization());
               registration.setRegDate(LocalDateTime.now());  // Set current time
               registration.setStudent(user);  // Link to user
               registration.setEvent(event);   // Link to event
               registrationsRepository.save(registration);

               return new ResponseEntity<>("Registration successful!", HttpStatus.CREATED);
           } catch (Exception e) {
               return new ResponseEntity<>("Error: Internal server error - " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }
   }

   