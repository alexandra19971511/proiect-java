package com.proiect.repository;

import com.proiect.model.FeedbackQuestion;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackQuestionRepository extends CrudRepository<FeedbackQuestion, Long> {
}