package com.proiect.controller;

import com.proiect.dto.ProfessorDto;
import com.proiect.service.DashboardService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard/professor")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {
  private final DashboardService service;

  @PostMapping
  public ProfessorDto save(@RequestBody ProfessorDto professorDto) {
    return service.save(professorDto);
  }

  @GetMapping
  public Collection<ProfessorDto> getAll() {
    return service.getAll();
  }

//  @PatchMapping
//  public CourseDto update(@PathVariable Long professorId, @RequestBody CourseDto course) {
//    return service.update(professorId, course);
//  }


  @DeleteMapping("/{professorId}/course/{courseId}")
  public void delete(@PathVariable Long professorId, @PathVariable Long courseId) {
    service.delete(professorId, courseId);
  }
}
