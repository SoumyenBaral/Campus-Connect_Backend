package com.campus.connect.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Registrations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String fullName;
   private String emailAddress;
   private long mobileNumber;
   private String organization;
    private LocalDateTime regDate = LocalDateTime.now();

    @ManyToOne
    private Users student;

    @ManyToOne
    private Events event;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public Users getStudent() {
		return student;
	}

	public void setStudent(Users student) {
		this.student = student;
	}

	public Events getEvent() {
		return event;
	}

	public void setEvent(Events event) {
		this.event = event;
	}

	public Registrations(Long id, String fullName, String emailAddress, long mobileNumber, String organization,
			LocalDateTime regDate, Users student, Events event) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.mobileNumber = mobileNumber;
		this.organization = organization;
		this.regDate = regDate;
		this.student = student;
		this.event = event;
	}

	public Registrations() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Registrations [id=" + id + ", fullName=" + fullName + ", emailAddress=" + emailAddress
				+ ", mobileNumber=" + mobileNumber + ", organization=" + organization + ", regDate=" + regDate
				+ ", student=" + student + ", event=" + event + "]";
	}


}