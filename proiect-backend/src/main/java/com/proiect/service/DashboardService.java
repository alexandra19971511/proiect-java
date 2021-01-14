package com.proiect.service;

import com.proiect.dto.CourseDto;
import com.proiect.dto.ProfessorDto;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class DashboardService {
  private final CourseService courseService;
  private final ProfessorService professorService;

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ProfessorDto save(ProfessorDto professorDto) {
    ProfessorDto savedProfessor = professorService.getByFullName(professorDto.getFullName());
    if (savedProfessor == null) {
      savedProfessor = professorService.save(professorDto);
    }

    CourseDto savedCourse = courseService.save(savedProfessor.getId(), professorDto.getCourseDto());
    savedProfessor.setCourseDto(savedCourse);
    return savedProfessor;
  }

  public Collection<ProfessorDto> getAll() {
    return courseService.getAll().stream()
        .map(courseDto -> {
          ProfessorDto dto = professorService.getById(courseDto.getProfessorId());
          dto.setCourseDto(courseDto);
          return dto;
        })
        .sorted(Comparator.comparing(ProfessorDto::getFullName)
            .thenComparing(dto -> dto.getCourseDto().getName()))
        .collect(Collectors.toList());
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void delete(Long professorId, Long courseId) {
    courseService.delete(courseId);
    Collection<CourseDto> courses = courseService.getAllByProfessorId(professorId);

    if (CollectionUtils.isEmpty(courses)) {
      professorService.delete(professorId);
    }
  }

}
