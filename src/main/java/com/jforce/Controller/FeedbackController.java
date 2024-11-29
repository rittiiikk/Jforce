package com.jforce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jforce.model.Feedback;
import com.jforce.repository.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Get all feedback
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Add new feedback
    @PostMapping
    public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
        if (feedback.getUsername() == null || feedback.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }
        Feedback savedFeedback = feedbackRepository.save(feedback);
        return ResponseEntity.ok(savedFeedback);
    }

    // Update feedback by ID
    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedbackDetails) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);

        if (optionalFeedback.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Feedback feedback = optionalFeedback.get();
        
        feedback.setUsername(feedbackDetails.getUsername());
        feedback.setMessage(feedbackDetails.getMessage());
        Feedback updatedFeedback = feedbackRepository.save(feedback);

        return ResponseEntity.ok(updatedFeedback);
    }

    // Delete feedback by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        if (!feedbackRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        feedbackRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
