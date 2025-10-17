package com.campus.connect.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitation {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String receiverEmail;

	    @ManyToOne
	    private Event event;

	    @ManyToOne
	    private User user;  // sender

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

		public Event getEvent() {
			return event;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Invitation(Long id, String receiverEmail, Event event, User user) {
			super();
			this.id = id;
			this.receiverEmail = receiverEmail;
			this.event = event;
			this.user = user;
		}

		public Invitation() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Invitation [id=" + id + ", receiverEmail=" + receiverEmail + ", event=" + event + ", user=" + user
					+ "]";
		}
	    
	    
	    

}
