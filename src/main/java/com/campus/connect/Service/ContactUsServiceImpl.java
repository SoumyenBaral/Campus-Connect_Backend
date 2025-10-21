package com.campus.connect.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.ContactUs;
import com.campus.connect.Repository.ContactUsRepository;

@Service
public class ContactUsServiceImpl implements ContactUsService{

	
	@Autowired
	private ContactUsRepository contactUsRepository;

	@Override
	public String submitContactForm(ContactUs contactUs) {
		// TODO Auto-generated method stub

contactUsRepository.save(contactUs);
		
		// Return a String message as requested by the structure
		return "Contact form submitted successfully!";

	}

//	@Override
//	public List<ContactUs> getAllSubmissions() {
//		// TODO Auto-generated method stub
//
//		return contactUsRepository.findAll();
//
//	}

//	@Override
//	public ContactUs getSubmissionById(Long id) {
//		// TODO Auto-generated method stub
//	Optional<ContactUs> submission = contactUsRepository.findById(id);
//		
//		// Return the entity if present, otherwise return null or throw an exception
//		// For simplicity, we return the entity or null.
//		return submission.orElse(null);
//	}
	

	
}
