package com.campus.connect.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campus.connect.Entity.Feedbacks;

public interface FeedbackRepository extends JpaRepository<Feedbacks, Long> {

}
