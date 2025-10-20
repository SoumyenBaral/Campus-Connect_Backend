package com.campus.connect.Entity;

import java.time.LocalDateTime;

public class productdevelopment {
	private int id;

    private String title;

    
    private String description;

    private LocalDateTime eventDate;

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

	public productdevelopment(int id, String title, String description, LocalDateTime eventDate) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "productdevelopment [id=" + id + ", title=" + title + ", description=" + description + ", eventDate="
				+ eventDate + "]";
	}
    
    
    
}
