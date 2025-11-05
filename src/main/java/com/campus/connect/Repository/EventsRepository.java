package com.campus.connect.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Events;
import com.campus.connect.Entity.Enum.EventStatus;

public interface EventsRepository extends JpaRepository<Events, Long>{
	
	    List<Events> findByStatusIn(List<EventStatus> statuses);
}

