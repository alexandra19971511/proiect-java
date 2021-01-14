package com.proiect.controller;

import com.proiect.dto.FeedbackQuestionDto;
import com.proiect.service.FeedbackQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class FeedbackQuestionController {
    private final FeedbackQuestionService service;

    @PostMapping
    public FeedbackQuestionDto save(@RequestBody FeedbackQuestionDto question) {
        return service.save(question);
    }

    @GetMapping
    public Collection<FeedbackQuestionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FeedbackQuestionDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
