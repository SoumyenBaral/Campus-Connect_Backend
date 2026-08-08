package com.campus.connect.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "contact_us")
public class ContactUs {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	 	
	    private String name;

	    private String email;

	    private String message;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public ContactUs(Long id, String name, String email, String message) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.message = message;
		}

		public ContactUs() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "ContactUs [id=" + id + ", name=" + name + ", email=" + email + ", message=" + message + "]";
		}

	    
	    
	
}
