package com.proiect.converter;

import com.proiect.dto.ProfessorDto;
import com.proiect.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorToProfessorDtoConverter {
  public ProfessorDto convert(Professor source) {
    ProfessorDto result = new ProfessorDto();
    result.setId(source.getId());
    result.setFullName(source.getFullName());
    return result;
  }
}
