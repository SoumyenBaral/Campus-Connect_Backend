package com.campus.connect.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.campus.connect.Entity.Enum.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Events {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;

	    private String title;

	    
private  String location;
	    private LocalDateTime eventDate;

	    @Enumerated(EnumType.STRING)
	    private EventStatus status;
	    


	    @ManyToOne
	    private Users host;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    private List<Registrations> registrations;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    private List<Invitations> invitations;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public LocalDateTime getEventDate() {
			return eventDate;
		}

		public void setEventDate(LocalDateTime eventDate) {
			this.eventDate = eventDate;
		}

		public EventStatus getStatus() {
			return status;
		}

		public void setStatus(EventStatus status) {
			this.status = status;
		}

		public Users getHost() {
			return host;
		}

		public void setHost(Users host) {
			this.host = host;
		}

		public List<Registrations> getRegistrations() {
			return registrations;
		}

		public void setRegistrations(List<Registrations> registrations) {
			this.registrations = registrations;
		}

		public List<Invitations> getInvitations() {
			return invitations;
		}

		public void setInvitations(List<Invitations> invitations) {
			this.invitations = invitations;
		}

		public Events(int id, String title, String location, LocalDateTime eventDate, EventStatus status, Users host,
				List<Registrations> registrations, List<Invitations> invitations) {
			super();
			this.id = id;
			this.title = title;
			this.location = location;
			this.eventDate = eventDate;
			this.status = status;
			this.host = host;
			this.registrations = registrations;
			this.invitations = invitations;
		}

		public Events() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Events [id=" + id + ", title=" + title + ", location=" + location + ", eventDate=" + eventDate
					+ ", status=" + status + ", host=" + host + ", registrations=" + registrations + ", invitations="
					+ invitations + "]";
		}


    
    
}
