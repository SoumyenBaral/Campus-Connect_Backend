package com.campus.connect.Entity;

import java.time.LocalDateTime;
import java.util.List;

import com.campus.connect.Entity.Enum.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class User {
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

    // Relationships
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private List<Event> hostedEvents;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<Invitation> sentInvitations;

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

	public List<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<Registration> registrations) {
		this.registrations = registrations;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Event> getHostedEvents() {
		return hostedEvents;
	}

	public void setHostedEvents(List<Event> hostedEvents) {
		this.hostedEvents = hostedEvents;
	}

	public List<Invitation> getSentInvitations() {
		return sentInvitations;
	}

	public void setSentInvitations(List<Invitation> sentInvitations) {
		this.sentInvitations = sentInvitations;
	}

	public User(Long id, String name, String email, String password, Role role, String contact, LocalDateTime createdAt,
			List<Registration> registrations, List<Feedback> feedbacks, List<Event> hostedEvents,
			List<Invitation> sentInvitations) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.contact = contact;
		this.createdAt = createdAt;
		this.registrations = registrations;
		this.feedbacks = feedbacks;
		this.hostedEvents = hostedEvents;
		this.sentInvitations = sentInvitations;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", contact=" + contact + ", createdAt=" + createdAt + ", registrations=" + registrations
				+ ", feedbacks=" + feedbacks + ", hostedEvents=" + hostedEvents + ", sentInvitations=" + sentInvitations
				+ "]";
	}

    
    
}
