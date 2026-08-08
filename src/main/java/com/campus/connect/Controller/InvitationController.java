package com.campus.connect.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Invitations;
import com.campus.connect.Service.InvitationsService;

@RestController
@RequestMapping("/api")
public class InvitationController {

	
	@Autowired
	private InvitationsService invitationsService;
	
	
	@PostMapping("/sendinvitations")
	private String addinvitations(@RequestBody Invitations invitations) {
		
		invitationsService.CreateInvitataions(invitations);
		
		return "invitation send successfully";
	}
	
	
}
