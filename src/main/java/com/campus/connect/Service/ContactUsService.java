package com.campus.connect.Service;

import java.util.List;

import com.campus.connect.Entity.ContactUs;

public interface ContactUsService {

	// create - post (Submit the form)
		// Return type is String as per the shared structure
		String submitContactForm(ContactUs contactUs);

		// read - get (Get all submissions)
//		List<ContactUs> getAllSubmissions();
		
		// Optional: Get a single submission by ID
//		ContactUs getSubmissionById(Long id);

		// Note: Update and Delete methods are omitted as they are uncommon for ContactUs forms


}
