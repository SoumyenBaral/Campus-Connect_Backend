package com.campus.connect.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.campus.connect.Entity.Enum.EventStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	 private Long id;

	    private String title;

	    private  String location;
	    
        @Column(name = "event_date", nullable = false)
	    private LocalDateTime eventDate;
        
     // NEW FIELD ADDED: Must match the data sent by Angular
        @Column(name = "category", nullable = false)
        private String category;

	    @Enumerated(EnumType.STRING)
	    private EventStatus status;
	    
	    @ManyToOne
	    private Users host;
	    
	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    @JsonManagedReference("event-registrations")
	    private List<Registrations> registrations;

	    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
	    @JsonManagedReference("event-registrations")
	    private List<Invitations> invitations;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
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

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
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

		public Events(Long id, String title, String location, LocalDateTime eventDate, String category,
				EventStatus status, Users host, List<Registrations> registrations, List<Invitations> invitations) {
			super();
			this.id = id;
			this.title = title;
			this.location = location;
			this.eventDate = eventDate;
			this.category = category;
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
					+ ", category=" + category + ", status=" + status + ", host=" + host + ", registrations="
					+ registrations + ", invitations=" + invitations + "]";
		}
}
