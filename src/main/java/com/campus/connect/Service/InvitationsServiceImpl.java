package com.campus.connect.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Invitations;
import com.campus.connect.Repository.InvitationsRepository;

@Service
public class InvitationsServiceImpl  implements InvitationsService{


	@Autowired
	private InvitationsRepository invitationsRepository;
	
	
	@Override
	public String CreateInvitataions(Invitations invitations) {
		// TODO Auto-generated method stub
		
		invitationsRepository.save(invitations);
		return null;
	}



}