package com.proiect.service;

import com.proiect.converter.CourseToCourseDtoConverter;
import com.proiect.dto.CourseDto;
import com.proiect.model.Course;
import com.proiect.model.Professor;
import com.proiect.repository.CourseRepository;
import com.proiect.repository.ProfessorRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CourseService {
  private final ProfessorRepository professorRepository;
  private final CourseRepository courseRepository;
  private final CourseToCourseDtoConverter converter;

  @Transactional
  public Collection<CourseDto> getAll() {
    Iterable<Course> courses = courseRepository.findAll();
    List<CourseDto> result = StreamSupport.stream(courses.spliterator(), false)
        .map(converter::convert)
        .collect(Collectors.toList());

    return result;
  }

  @Transactional // to have the operation read-only
  public Collection<CourseDto> getAllByProfessorId(Long professorId) {
    checkIfProfessorExists(professorId);

    List<CourseDto> result = StreamSupport.stream(courseRepository.findByProfessorId(professorId).spliterator(), false)
        .map(converter::convert)
        .collect(Collectors.toList());

    return result;
  }

  private Professor checkIfProfessorExists(Long professorId) {
    return Optional.ofNullable(professorRepository.findOne(professorId))
        .orElseThrow(() -> new IllegalArgumentException("No professor with id= " + professorId + " found"));
  }

  @Transactional
  public CourseDto getById(Long professorId, Long id) {
    checkIfProfessorExists(professorId);

    return courseRepository.findByIdAndProfessorId(id, professorId)
        .map(converter::convert)
        .orElseThrow(() -> new IllegalArgumentException("No course with id= " + id + " found"));
  }

  public CourseDto save(Long professorId, CourseDto dto) {
    Course newCourse = new Course();
    newCourse.setName(dto.getName());
    newCourse.setProfessor(new Professor(professorId));
    Course courseSaved = courseRepository.save(newCourse);

    return Optional.ofNullable(courseSaved)
        .map(converter::convert)
        .get();
  }

  public void delete(Long id) {
    courseRepository.delete(id);
  }

}