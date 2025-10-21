package com.campus.connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Registrations;
import com.campus.connect.Service.RegistrationsService;

@RestController
@RequestMapping("/api")
public class RegistrationsController {

	
	@Autowired
	private RegistrationsService  registrationService;
	
	@PostMapping("/postregistration")
	private String Registrationform(@RequestBody  Registrations registration) {
		return registrationService.CreateRegistration(registration);
		
	}
	
}
