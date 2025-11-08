package com.campus.connect.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitations {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String receiverEmail;
	    
	    
	    private String massage;
	    

	    @ManyToOne
	    @JsonBackReference
	    private Events event;

	    @ManyToOne
	    @JsonBackReference
	    private Users user;  // sender

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getReceiverEmail() {
			return receiverEmail;
		}

		public void setReceiverEmail(String receiverEmail) {
			this.receiverEmail = receiverEmail;
		}

		public String getMassage() {
			return massage;
		}

		public void setMassage(String massage) {
			this.massage = massage;
		}

//		public Events getEvent() {
//			return event;
//		}
//
//		public void setEvent(Events event) {
//			this.event = event;
//		}

//		public Users getUser() {
//			return user;
//		}
//
//		public void setUser(Users user) {
//			this.user = user;
//		}

		public Invitations(Long id, String receiverEmail, String massage) {
			super();
			this.id = id;
			this.receiverEmail = receiverEmail;
			this.massage = massage;
//			this.event = event;
//			this.user = user;
		}

		public Invitations() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Invitations [id=" + id + ", receiverEmail=" + receiverEmail + ", massage=" + massage + "]";
		}

	

}
