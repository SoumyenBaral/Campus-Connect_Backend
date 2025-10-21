package com.campus.connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Events;

public interface EventRepository extends JpaRepository<Events, Integer>{

}
