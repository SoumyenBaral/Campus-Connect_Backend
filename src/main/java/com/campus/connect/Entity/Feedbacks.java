package com.campus.connect.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedbacks {

	
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne // Maps to user_id in DB
	    private Users user;

	    private Integer starRating; // CHECK(star_rating BETWEEN 1 AND 5)
	    

	    private LocalDateTime createdAt = LocalDateTime.now();


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Users getUser() {
			return user;
		}


		public void setUser(Users user) {
			this.user = user;
		}


		public Integer getStarRating() {
			return starRating;
		}


		public void setStarRating(Integer starRating) {
			this.starRating = starRating;
		}


		public LocalDateTime getCreatedAt() {
			return createdAt;
		}


		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}


		public Feedbacks(Long id, Users user, Integer starRating, LocalDateTime createdAt) {
			super();
			this.id = id;
			this.user = user;
			this.starRating = starRating;
			this.createdAt = createdAt;
		}


		public Feedbacks() {
			super();
			// TODO Auto-generated constructor stub
		}


		@Override
		public String toString() {
			return "Feedbacks [id=" + id + ", user=" + user + ", starRating=" + starRating + ", createdAt=" + createdAt
					+ "]";
		}

		
	    
}