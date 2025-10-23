package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.ContactUs;
import com.campus.connect.Service.ContactUsService;


@RestController
@RequestMapping("/api") 

public class ContactUsController {
	
	@Autowired
private ContactUsService contactUsService;
	
	
	
	@PostMapping("/postcontact")
	private String submitContactForm(@RequestBody ContactUs contactUs) {
        // The service method returns a String success message.
		return contactUsService.submitContactForm(contactUs);
	}
	
//	@GetMapping("/submissions")
//	public List<ContactUs> getAllSubmissions(){
//		return contactUsService.getAllSubmissions();
//	}
//	
}
