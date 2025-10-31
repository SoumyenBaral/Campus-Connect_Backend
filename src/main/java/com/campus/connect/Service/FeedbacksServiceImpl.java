// FeedbacksServiceImpl.java (Implementation)
package com.campus.connect.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campus.connect.Entity.Feedbacks;
// 👇 You will need to import the Users entity and UserRepository
import com.campus.connect.Entity.Users;
import com.campus.connect.Repository.FeedbacksRepository;
import com.campus.connect.Repository.UsersRepository; // Assuming you have this repository

@Service
public class FeedbacksServiceImpl  implements FeedbacksService{
    
    @Autowired 
    private FeedbacksRepository feedbacksRepository;
    
    @Autowired // Inject the UsersRepository to validate and fetch the User entity
    private UsersRepository usersRepository; 

    @Override
    public String createFeedback(Feedbacks feedback) {
        // IMPORTANT: The incoming 'feedback' object from Angular will only have
        // the User ID inside the 'user' field, e.g., feedback.getUser().getId().
        Long userId = feedback.getUser().getId();
        
        // 1. Retrieve the full Users entity from the database using the ID
        // This is crucial because the Feedbacks entity expects a full Users object for the ManyToOne relationship.
        Users user = usersRepository.findById(userId).orElse(null);
        
        if (user == null) {
            // Handle case where user ID is invalid or not found
            return "User not found or User ID is invalid.";
        }
        
        // 2. Set the complete Users object back on the feedback entity
        feedback.setUser(user);
        
        // 3. Validate star rating (redundant if DB CHECK is active, but good practice)
        if (feedback.getStarRating() == null || feedback.getStarRating() < 1 || feedback.getStarRating() > 5) {
             return "Invalid star rating value.";
        }
        
        // 4. Save the entity
        feedbacksRepository.save(feedback);
        return "User feedback success";
    }

    @Override
    public List<Feedbacks> getAllUserFeedback() {
        // TODO: Implement logic to get all feedback (likely for Admin view)
        return feedbacksRepository.findAll();
    }

    @Override
    public String deleteFeedback(Long id) {
        // TODO: Implement logic to delete feedback (likely for Admin moderation)
        if(feedbacksRepository.existsById(id)) {
            feedbacksRepository.deleteById(id);
            return "feedback data deleted";
        }
        else {
            return "User feedback data not found";
        }
    }
}