package com.jforce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jforce.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

