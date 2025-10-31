package com.campus.connect.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.connect.Entity.Feedbacks;
import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.FeedbacksRepository;
import com.campus.connect.Repository.UsersRepository;
@Service
public class FeedbacksServiceImpl  implements FeedbacksService{
	@Autowired 
	private FeedbacksRepository feedbacksRepository;
	private UsersRepository usersRepository;

	@Override
	@Transactional
	public String createFeedback(Feedbacks feedback) {
		// TODO Auto-generated method stub
		
		if (feedback.getUser() == null || feedback.getUser().getId() == null) {
            return "Error: User ID is missing in feedback payload.";
       }
       Long userId = feedback.getUser().getId();
       
       // 2. Fetch the fully managed Users entity from the database
       Users user = usersRepository.findById(userId)
                   .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
       
       // 3. Set the managed Users entity back onto the Feedback object
       feedback.setUser(user);
       
       // 4. Save the new feedback entity
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
