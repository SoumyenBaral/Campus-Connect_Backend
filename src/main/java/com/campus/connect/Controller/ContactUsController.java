package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.ContactUs;
import com.campus.connect.Service.ContactUsService;


@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "http://localhost:4200/")
public class ContactUsController {
	
	@Autowired
private ContactUsService contactUsService;
	
	
	
	@PostMapping("/postcontact")
	private String submitContactForm(@RequestBody ContactUs contactUs) {
        // The service method returns a String success message.
		return contactUsService.submitContactForm(contactUs);
	}
	
	@GetMapping("/getAllcontact")
	public List<ContactUs> getAllSubmissions(){
		return contactUsService.getAllSubmissions();
	}
	
	
	
	@DeleteMapping("/deletecontactus/{id}")
public String deletecontact(@PathVariable  Long id) {
return	contactUsService.deleteContactus(id);
	
}
	
	
	
	
}
