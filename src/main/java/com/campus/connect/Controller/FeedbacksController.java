package com.campus.connect.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campus.connect.Entity.Feedbacks;
import com.campus.connect.Service.FeedbacksService;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class FeedbacksController{
@Autowired
	private FeedbacksService feedbacksService;

@PostMapping("/postfeedback")
	private  String addFeedback(@RequestBody Feedbacks feedbacks) {
	return feedbacksService.createFeedback(feedbacks);
}

@GetMapping("/getfeedback")
private List<Feedbacks> getAllUserFeedbacks(){
return feedbacksService.getAllUserFeedback();
}

//@DeleteMapping("/deletefeedback/{id}")
//public String deleteFeedback(@PathVariable Long id) {
//return feedbacksService.deleteFeedback(id);
//}


}
