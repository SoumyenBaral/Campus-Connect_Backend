package com.campus.connect.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityNotFoundException; // Use this or a custom exception

import com.campus.connect.Entity.Feedbacks;
import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.FeedbacksRepository;
import com.campus.connect.Repository.UsersRepository;

@Service
public class FeedbacksServiceImpl  implements FeedbacksService {
    
    // Use 'final' and inject via constructor (better practice)
    private final FeedbacksRepository feedbacksRepository;
    private final UsersRepository usersRepository; 

    // Constructor Injection
    @Autowired
    public FeedbacksServiceImpl(FeedbacksRepository feedbacksRepository, UsersRepository usersRepository) {
        this.feedbacksRepository = feedbacksRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional // Good practice for methods that change data
    public String createFeedback(Feedbacks feedback) {
        
        // Check for missing User data in the payload
        if (feedback.getUser() == null || feedback.getUser().getId() == null) {
             // Returning an exception is cleaner than a String for error handling
             throw new IllegalArgumentException("User ID is missing in the feedback payload.");
        }
        
        Long userId = feedback.getUser().getId();
        
        // 1. Retrieve the full Users entity from the database using the ID
        // Using orElseThrow() is often cleaner than checking for null.
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        
        // 2. Set the complete Users object back on the feedback entity
        feedback.setUser(user);
        
        // 3. Validate star rating 
        if (feedback.getStarRating() == null || feedback.getStarRating() < 1 || feedback.getStarRating() > 5) {
             throw new IllegalArgumentException("Invalid star rating value. Must be between 1 and 5.");
        }
        
        // 4. Save the entity
        feedbacksRepository.save(feedback);
        return "User feedback success";
    }

	@Override
	public List<Feedbacks> getAllUserFeedback() {
		return feedbacksRepository.findAll();
	}

    @Override
    @Transactional // Good practice for delete operations
    public String deleteFeedback(Long id) {
        if(feedbacksRepository.existsById(id)) {
            feedbacksRepository.deleteById(id);
            return "feedback data deleted";
        }
        else {
            return "User feedback data not found";
        }
    }
}