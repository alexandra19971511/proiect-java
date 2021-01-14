package com.proiect.service;

import com.proiect.converter.ProfessorToProfessorDtoConverter;
import com.proiect.dto.ProfessorDto;
import com.proiect.model.Professor;
import com.proiect.repository.ProfessorRepository;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfessorService {
  private final ProfessorRepository professorRepository;
  private final ProfessorToProfessorDtoConverter converter;

  public Collection<ProfessorDto> getAll() {
    return StreamSupport.stream(professorRepository.findAll().spliterator(), false)
        .map(converter::convert)
        .collect(Collectors.toList());
  }

  public ProfessorDto getByFullName(String fullName) {
    return professorRepository.findByFullName(fullName)
        .map(converter::convert)
        .orElse(null);
  }

  public ProfessorDto getById(Long id) {
    return Optional.ofNullable(professorRepository.findOne(id))
        .map(converter::convert)
        .orElseThrow(() -> new IllegalArgumentException("No professor with id= " + id + " found"));
  }

  public ProfessorDto save(ProfessorDto dto) {
    Professor newProfessor = new Professor(dto.getFullName());
    return Optional.of(professorRepository.save(newProfessor))
        .map(converter::convert)
        .orElseThrow(() -> new IllegalArgumentException("Error on saving professor with fullName= " + dto.getFullName()));
  }

  public ProfessorDto update(ProfessorDto dto) {
    Professor oldProfessor = professorRepository.findOne(dto.getId());
    Professor newProfessor = new Professor(oldProfessor.getId(), dto.getFullName());

    return Optional.of(professorRepository.save(newProfessor))
        .map(converter::convert)
        .orElseThrow(() -> new IllegalArgumentException("Error on saving professor with fullName= " + dto.getFullName()));
  }

  public void delete(Long id) {
    professorRepository.delete(id);
  }
}
