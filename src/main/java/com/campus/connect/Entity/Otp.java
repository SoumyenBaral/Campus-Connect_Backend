package com.campus.connect.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "otp_verification")
public class Otp {

	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String email;

	    private String otp;

	    @Column(name = "expiry_time")
	    private LocalDateTime expiryTime;
	    
	    @Column(name = "is_used")
	    private boolean isUsed;

	    @Column(name="created_at")
	    private LocalDateTime createdAt;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getOtp() {
			return otp;
		}

		public void setOtp(String otp) {
			this.otp = otp;
		}

		public LocalDateTime getExpiryTime() {
			return expiryTime;
		}

		public void setExpiryTime(LocalDateTime expiryTime) {
			this.expiryTime = expiryTime;
		}

		public boolean isUsed() {
			return isUsed;
		}

		public void setUsed(boolean isUsed) {
			this.isUsed = isUsed;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public Otp(Long id, String email, String otp, LocalDateTime expiryTime, boolean isUsed,
				LocalDateTime createdAt) {
			super();
			this.id = id;
			this.email = email;
			this.otp = otp;
			this.expiryTime = expiryTime;
			this.isUsed = isUsed;
			this.createdAt = createdAt;
		}

		public Otp() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Otp [id=" + id + ", email=" + email + ", otp=" + otp + ", expiryTime=" + expiryTime + ", isUsed="
					+ isUsed + ", createdAt=" + createdAt + "]";
		}
	    
	    
	    
	}