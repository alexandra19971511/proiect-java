package com.proiect.repository;

import com.proiect.model.Answer;
import java.util.Optional;
import java.util.stream.Stream;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
  Stream<Answer> findByProfessorId(Long professorId);

  Stream<Answer> findByProfessorIdAndCourseId(Long professorId, Long courseId);

  Optional<Answer> findByUserIdAndProfessorIdAndCourseId(Long userId, Long professorId, Long courseId);
}