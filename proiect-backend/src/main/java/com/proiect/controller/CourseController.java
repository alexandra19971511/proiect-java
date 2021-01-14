package com.proiect.controller;

import com.proiect.dto.CourseDto;
import com.proiect.service.CourseService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CourseController {
  private final CourseService service;

  @GetMapping("/professor/{professorId}")
  public Collection<CourseDto> getAllByProfessorId(@PathVariable Long professorId) {
    return service.getAllByProfessorId(professorId);
  }
}

