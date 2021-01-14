package com.proiect.converter;

import com.proiect.dto.FeedbackQuestionDto;
import com.proiect.model.FeedbackQuestion;
import org.springframework.stereotype.Component;

@Component
public class FeedbackQuestionToFeedbackQuestionDtoConverter {
    public FeedbackQuestionDto convert(FeedbackQuestion source) {
        FeedbackQuestionDto result = new FeedbackQuestionDto(source.getId(), source.getQuestion());
        return result;
    }
}
