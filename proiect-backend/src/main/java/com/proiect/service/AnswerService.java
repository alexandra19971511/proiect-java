package com.proiect.service;

import com.proiect.converter.AnswerToAnswerDtoConverter;
import com.proiect.dto.AnswerDto;
import com.proiect.dto.MeanDto;
import com.proiect.model.Answer;
import com.proiect.model.Course;
import com.proiect.model.Professor;
import com.proiect.model.User;
import com.proiect.repository.AnswerRepository;
import com.proiect.repository.ProfessorRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@AllArgsConstructor
public class AnswerService {
  private final ProfessorRepository professorRepository;
  private final CourseService courseService;
  private final AnswerRepository answerRepository;
  private final AnswerToAnswerDtoConverter converter;

  @Transactional // to have the operation read-only
  public Collection<AnswerDto> getAll(Long professorId) {
    checkIfProfessorExists(professorId);

    List<AnswerDto> result = StreamSupport.stream(answerRepository.findByProfessorId(professorId).spliterator(), false)
        .map(converter::convert)
        .collect(Collectors.toList());

    return result;
  }

  private Professor checkIfProfessorExists(Long professorId) {
    return Optional.ofNullable(professorRepository.findOne(professorId))
        .orElseThrow(() -> new IllegalArgumentException("No professor with id= " + professorId + " found"));
  }

  public AnswerDto getById(Long id) {
    return Optional.ofNullable(answerRepository.findOne(id))
        .map(converter::convert)
        .orElseThrow(() -> new IllegalArgumentException("No answer with id= " + id + " found"));
  }

  public AnswerDto save(Long userId, Long professorId, Long courseId, AnswerDto dto) {
    checkIfProfessorExists(professorId);

    Answer answer = checkIfAnswerExists(userId, professorId, courseId);
    answer.setAnswers(dto.getAnswers());
    Answer answerSaved = answerRepository.save(answer);

    return Optional.ofNullable(answerSaved)
        .map(converter::convert)
        .get();
  }

  private Answer createNewAnswer(Long userId, Long professorId, Long courseId) {
    Answer newAnswer = new Answer();
    newAnswer.setProfessor(new Professor(professorId));
    newAnswer.setUser(new User(userId));
    newAnswer.setCourse(new Course(courseId));
    return newAnswer;
  }

  private Answer checkIfAnswerExists(Long userId, Long professorId, Long courseId) {
    return answerRepository.findByUserIdAndProfessorIdAndCourseId(userId, professorId, courseId)
        .orElse(createNewAnswer(userId, professorId, courseId));
  }

  public void delete(Long id) {
    answerRepository.delete(id);
  }

  @Transactional
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public MeanDto getMean(Long professorId, Long courseId) {
    courseService.getById(professorId, courseId);

    List<Integer> meanPerUsers = answerRepository.findByProfessorIdAndCourseId(professorId, courseId)
        .map(answer -> {
          Collection<Integer> marks = answer.getAnswers().values();
          return computeMean(marks);
        })
        .collect(Collectors.toList());

    Integer mean = computeMean(meanPerUsers);
    return new MeanDto(mean, meanPerUsers.size());
  }

  private Integer computeMean(Collection<Integer> marks) {
    if (CollectionUtils.isEmpty(marks)) {
      return 0;
    }

    Integer marksSum = 0;
    for (Integer mark : marks) {
      marksSum += mark;
    }
    return marksSum / marks.size();
  }
}