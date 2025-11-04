package com.campus.connect.Service;

import java.util.List;


import com.campus.connect.Entity.Feedbacks;

public interface FeedbacksService {

	
	String createFeedback(Feedbacks feedback);
	List<Feedbacks> getAllUserFeedback();
	
	String deleteFeedback(Long id);
	
}
