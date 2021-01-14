package com.proiect.dto;

import java.util.Collections;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
  private Map<Integer, Integer> answers = Collections.emptyMap();
}
