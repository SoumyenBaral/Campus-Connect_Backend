package com.campus.connect.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.connect.Entity.Feedbacks;
import com.campus.connect.Entity.Users;
import com.campus.connect.Entity.Enum.Role;
import com.campus.connect.Repository.FeedbacksRepository;
import com.campus.connect.Repository.UsersRepository;

@Service
public class FeedbacksServiceImpl implements FeedbacksService {
    
    @Autowired 
    private FeedbacksRepository feedbacksRepository;
    
    @Autowired 
    private UsersRepository usersRepository; // Correctly Autowired now

    @Override
    @Transactional
    public String createFeedback(Feedbacks feedback) {
        
        if (feedback.getUser() == null || feedback.getUser().getId() == null) {
            return "Error: User ID is missing in feedback payload.";
        }
        Long userId = feedback.getUser().getId();
        
        // 1. Fetch the fully managed Users entity
        Users user = usersRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        
        // 2. CHECK: One-Time Submission Limit 🛑
        if (feedbacksRepository.countByUserId(userId) > 0) {
            return "Error: You have already submitted your feedback and cannot submit again.";
        }
        
        // 3. CHECK: Role Guard (Only Students allowed) 👨‍🎓
        if (user.getRole() != Role.STUDENT) { 
            return "Error: Only students are allowed to post star ratings.";
        }
        
        // 4. Star Rating Check (1-5 range)
        if (feedback.getStarRating() == null || feedback.getStarRating() < 1 || feedback.getStarRating() > 5) {
            return "Error: Star rating must be between 1 and 5.";
        }
        
        // 5. Save the new feedback entity
        feedback.setUser(user);
        feedbacksRepository.save(feedback);
        return "User feedback success";
    }
    
    @Override
    public List<Feedbacks> getAllUserFeedback() {
        // WARNING: This endpoint is accessible to anyone who can hit it, 
        // as there is no Spring Security role check.
        return feedbacksRepository.findAll();
    }

    @Override
    public String deleteFeedback(Long id) {
        if(feedbacksRepository.existsById(id)) {
            feedbacksRepository.deleteById(id);
            return "Feedback data deleted";
        }
        else {
            return "User feedback data not found";
        }
    }
}
