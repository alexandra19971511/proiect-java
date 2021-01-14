package com.proiect.service;

import com.proiect.converter.FeedbackQuestionToFeedbackQuestionDtoConverter;
import com.proiect.dto.FeedbackQuestionDto;
import com.proiect.model.FeedbackQuestion;
import com.proiect.repository.FeedbackQuestionRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FeedbackQuestionService {
    private final FeedbackQuestionRepository feedbackQuestionRepository;
    private final FeedbackQuestionToFeedbackQuestionDtoConverter converter;

//    @PreAuthorize("hasRole('ADMIN')")
    public Collection<FeedbackQuestionDto> getAll() {
        return StreamSupport.stream(feedbackQuestionRepository.findAll().spliterator(), false)
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public FeedbackQuestionDto getById(Long id) {
        return Optional.ofNullable(feedbackQuestionRepository.findOne(id))
                .map(converter::convert)
                .orElseThrow(() -> new IllegalArgumentException("No question with id= " + id + " found"));
    }

    public FeedbackQuestionDto save(FeedbackQuestionDto dto) {
        FeedbackQuestion newQuestion = new FeedbackQuestion(dto.getQuestion());
        FeedbackQuestion questionSaved = feedbackQuestionRepository.save(newQuestion);

        return Optional.ofNullable(questionSaved)
                .map(converter::convert)
                .get();
    }

    public void delete(Long id) {
        feedbackQuestionRepository.delete(id);
    }
}