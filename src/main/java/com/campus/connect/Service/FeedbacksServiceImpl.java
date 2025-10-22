package com.campus.connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Feedbacks;
import com.campus.connect.Repository.FeedbacksRepository;
@Service
public class FeedbacksServiceImpl  implements FeedbacksService{
	@Autowired 
	private FeedbacksRepository feedbacksRepository;

	@Override
	public String createFeedback(Feedbacks feedback) {
		// TODO Auto-generated method stub
		feedbacksRepository.save(feedback);
		return "User feedback success";
		
	}

	@Override
	public List<Feedbacks> getAllUserFeedback() {
		// TODO Auto-generated method stub
		return feedbacksRepository.findAll();
	}

	@Override
	public String deleteFeedback(Long id) {
		// TODO Auto-generated method stub
		if(feedbacksRepository.existsById(id)) {
			feedbacksRepository.deleteById(id);
			return "feedback data deleted";
		}
		else {
			return "User feedback data not found";
		}
	}

}
