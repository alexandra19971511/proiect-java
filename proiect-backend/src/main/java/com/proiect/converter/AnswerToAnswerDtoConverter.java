package com.proiect.converter;

import com.proiect.dto.AnswerDto;
import com.proiect.model.Answer;
import org.springframework.stereotype.Component;

@Component
public class AnswerToAnswerDtoConverter {
  public AnswerDto convert(Answer source) {
    AnswerDto result = new AnswerDto(source.getAnswers());
    return result;
  }
}