package com.campus.connect.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


import com.campus.connect.Entity.Enum.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

      private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String contact;

    private LocalDateTime createdAt = LocalDateTime.now();
     private boolean isApproved;

    public Users(boolean isApproved) {
		super();
		this.isApproved = isApproved;
	}

	public boolean isApproved() {
		return isApproved;
	}

	 public void setApproved(boolean isApproved) {
		 this.isApproved = isApproved;
	 }

	// Relationships
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Registrations> registrations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Feedbacks> feedbacks;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Events> hostedEvents;

    public String getUsername() {
        // Use email for the Spring Security 'username' field
        return email; 
    }

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Registrations> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registrations> registrations) {
		this.registrations = registrations;
	}

	public List<Feedbacks> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedbacks> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Events> getHostedEvents() {
		return hostedEvents;
	}
	
	public void setHostedEvents(List<Events> hostedEvents) {
		this.hostedEvents = hostedEvents;
	}

	public Users(Long id, String name, String email, String password, Role role, String contact,
			LocalDateTime createdAt, boolean isApproved, List<Registrations> registrations, List<Feedbacks> feedbacks,
			List<Events> hostedEvents) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contact = contact;
		this.createdAt = createdAt;
		this.isApproved = isApproved;
		this.registrations = registrations;
		this.feedbacks = feedbacks;
		this.hostedEvents = hostedEvents;
	}

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", contact=" + contact + ", createdAt=" + createdAt + ", isApproved=" + isApproved
				+ ", registrations=" + registrations + ", feedbacks=" + feedbacks + ", hostedEvents=" + hostedEvents
				+ "]";
	}
}