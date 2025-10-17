package com.campus.connect.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.campus.connect.Entity.Enum.EventStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Event {
	
	 private int id;

	    private String title;

	    
	    private String description;

	    private LocalDateTime eventDate;

	    @Enumerated(EnumType.STRING)
	    private EventStatus status;
	    


	    @ManyToOne
	    private User host;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    private List<Registration> registrations;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    private List<Invitation> invitations;

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

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public User getHost() {
			return host;
		}

		public void setHost(User host) {
			this.host = host;
		}

		public List<Registration> getRegistrations() {
			return registrations;
		}

		public void setRegistrations(List<Registration> registrations) {
			this.registrations = registrations;
		}

		public List<Invitation> getInvitations() {
			return invitations;
		}

		public void setInvitations(List<Invitation> invitations) {
			this.invitations = invitations;
		}

		public Event(int id, String title, String description, LocalDateTime eventDate, EventStatus status, User host,
				List<Registration> registrations, List<Invitation> invitations) {
			super();
			this.id = id;
			this.title = title;
			this.description = description;
			this.eventDate = eventDate;
			this.status = status;
			this.host = host;
			this.registrations = registrations;
			this.invitations = invitations;
		}

		public Event() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Event [id=" + id + ", title=" + title + ", description=" + description + ", eventDate=" + eventDate
					+ ", status=" + status + ", host=" + host + ", registrations=" + registrations + ", invitations="
					+ invitations + "]";
		}



    
    
}
