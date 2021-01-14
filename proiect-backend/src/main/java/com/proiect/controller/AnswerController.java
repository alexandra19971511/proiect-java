package com.proiect.controller;

import com.proiect.dto.AnswerDto;
import com.proiect.dto.MeanDto;
import com.proiect.service.AnswerService;
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
@RequestMapping("/answer")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AnswerController {
  private final AnswerService service;

  @PostMapping("/{userId}/{professorId}/{courseId}")
  public AnswerDto save(@PathVariable Long userId, @PathVariable Long professorId, @PathVariable Long courseId,
      @RequestBody AnswerDto answerDto) {
    return service.save(userId, professorId, courseId, answerDto);
  }

  @GetMapping("/professor/{professorId}")
  public Collection<AnswerDto> getAllByProfessorId(@PathVariable Long professorId) {
    return service.getAll(professorId);
  }

  @GetMapping("/{answerId}")
  public AnswerDto getById(@PathVariable Long answerId) {
    return service.getById(answerId);
  }

  @DeleteMapping("/{answerId}")
  public void delete(@PathVariable Long answerId) {
    service.delete(answerId);
  }

  @GetMapping("/mean/{professorId}/{courseId}")
  public MeanDto getMean(@PathVariable Long professorId, @PathVariable Long courseId) {
    return service.getMean(professorId, courseId);
  }

}
