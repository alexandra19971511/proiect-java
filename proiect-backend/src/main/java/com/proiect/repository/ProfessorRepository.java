package com.proiect.repository;

import com.proiect.model.Professor;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorRepository  extends CrudRepository<Professor, Long> {
  Optional<Professor> findByFullName(String professorFullName);
}
