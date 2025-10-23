package com.campus.connect.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Registrations;
import com.campus.connect.Repository.RegistrationsRepository;


@Service
public class RegistrationsServiceImpl  implements RegistrationsService{

	
	@Autowired
private RegistrationsRepository registrationsRepository;

	@Override
	public String CreateRegistration(Registrations registration) {
		// TODO Auto-generated method stub
		registrationsRepository.save(registration);
		return "registrations successfull";
	}	
	
	
	
	
	
	
	

}
