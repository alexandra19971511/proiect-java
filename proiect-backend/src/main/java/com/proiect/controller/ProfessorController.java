package com.proiect.controller;

import com.proiect.dto.ProfessorDto;
import com.proiect.service.ProfessorService;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/professor")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class ProfessorController {
  private final ProfessorService service;

  @GetMapping
  public Collection<ProfessorDto> getAll() {
    return service.getAll();
  }
}

