package com.proiect.repository;

import com.proiect.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface CourseRepository extends CrudRepository<Course, Long> {
    Stream<Course> findByProfessorId(Long professorId);
    Optional<Course> findByIdAndProfessorId(Long id, Long professorId);
}