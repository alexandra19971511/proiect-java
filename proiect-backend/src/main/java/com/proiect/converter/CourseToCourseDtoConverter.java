package com.proiect.converter;

import com.proiect.dto.CourseDto;
import com.proiect.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseToCourseDtoConverter {
  public CourseDto convert(Course source) {
    CourseDto result = new CourseDto(source.getId(), source.getName(), source.getProfessor().getId());
    return result;
  }
}