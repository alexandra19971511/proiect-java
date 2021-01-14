package com.proiect.service;


import com.proiect.converter.AnswerToAnswerDtoConverter;
import com.proiect.dto.AnswerDto;
import com.proiect.dto.CourseDto;
import com.proiect.dto.MeanDto;
import com.proiect.model.Answer;
import com.proiect.model.Course;
import com.proiect.model.Professor;
import com.proiect.model.User;
import com.proiect.repository.AnswerRepository;
import com.proiect.repository.ProfessorRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AnswerServiceTest {
    private static final Long PROFESSOR_ID = 1L;
    private static final Long COURSE_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Long ANSWER_ID = 1L;

    @Mock
    private CourseService courseService;
    @Mock
    private ProfessorRepository professorRepository;
    @Mock
    private AnswerRepository answerRepository;
    @Spy
    private AnswerToAnswerDtoConverter answerToAnswerDtoConverter;
    @InjectMocks
    private AnswerService sut;

    @Before
    public void setUp() {
        doAnswer(invocation -> {
            Long professorId = invocation.getArgumentAt(0, Long.class);
            return new Professor(professorId);
        })
                .when(professorRepository).findOne(anyLong());

        doAnswer(invocation -> {
            Long professorId = invocation.getArgumentAt(0, Long.class);
            Long courseId = invocation.getArgumentAt(1, Long.class);

            return new CourseDto(courseId, "test", professorId);
        })
                .when(courseService).getById(anyLong(), anyLong());

        doAnswer(invocation -> {
            Answer answer = invocation.getArgumentAt(0, Answer.class);
            Long professorId = answer.getProfessor().getId();
            Long courseId = answer.getCourse().getId();
            Long userId = answer.getUser().getId();
            Map<Integer, Integer> answerMap = answer.getAnswers();

            return mockAnswersForProfessorAndCourseAndUser(professorId, courseId, userId,
                    answerMap.values().toArray(new Integer[0]));
        })
                .when(answerRepository).save(any(Answer.class));

        doAnswer(invocation -> {
            Long professorId = invocation.getArgumentAt(0, Long.class);
            Long courseId = invocation.getArgumentAt(1, Long.class);
            return mockStreamOfAnswers(professorId, courseId);
        })
                .when(answerRepository).findByProfessorIdAndCourseId(anyLong(), anyLong());
    }

    @Test
    public void getMean_ProfessorIdAndCourseIdGiven_ShouldCalculateMean() {
        MeanDto result = sut.getMean(PROFESSOR_ID, COURSE_ID);

        Integer expectedMean = 2;
        assertEquals("Invalid mean", expectedMean, result.getMean());
    }

    @Test
    public void save_ProfessorIdAndCourseIdAndUserIdGiven_ShouldSaveNewAnswer() {
        when(answerRepository.findByUserIdAndProfessorIdAndCourseId(anyLong(), anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        List<Integer> marks = Arrays.asList(2, 4, 3, 5, 3);
        AnswerDto result = sut.save(USER_ID, PROFESSOR_ID, COURSE_ID, mockAnswerDto(marks.toArray(new Integer[0])));

        Map<Integer, Integer> expectedAnswerMap = mockAnswersMap(marks.toArray(new Integer[0]));
        assertEquals("Invalid answer map", expectedAnswerMap, result.getAnswers());
    }

    @Test
    public void save_ProfessorIdAndCourseIdAndUserIdGiven_ShouldUpdateExistingAnswer() {
        doAnswer(invocation -> {
            Long professorId = invocation.getArgumentAt(0, Long.class);
            Long courseId = invocation.getArgumentAt(1, Long.class);
            Long userId = invocation.getArgumentAt(2, Long.class);
            return Optional.of(mockAnswersForProfessorAndCourseAndUser(professorId, courseId, userId, 1, 3, 5, 1, 5));
        })
                .when(answerRepository).findByUserIdAndProfessorIdAndCourseId(anyLong(), anyLong(), anyLong());

        List<Integer> marks = Arrays.asList(2, 4, 3, 5, 3);
        AnswerDto result = sut.save(USER_ID, PROFESSOR_ID, COURSE_ID, mockAnswerDto(marks.toArray(new Integer[0])));

        Map<Integer, Integer> expectedAnswerMap = mockAnswersMap(marks.toArray(new Integer[0]));
        assertEquals("Invalid answer map", expectedAnswerMap, result.getAnswers());
    }

    private Professor mockProfessor() {
        return new Professor(PROFESSOR_ID, "Mihai Cristea");
    }

    @Test
    public void delete_ShouldDeleteCourse() {
        when(answerRepository.findOne(ANSWER_ID))
                .thenReturn(mockAnswersForProfessorAndCourseAndUser(PROFESSOR_ID, COURSE_ID, USER_ID, 5, 2, 3, 4, 2));

        sut.delete(ANSWER_ID);

        verify(answerRepository).delete(ANSWER_ID);
    }

    private Stream<Answer> mockStreamOfAnswers(Long professorId, Long courseId) {
        return Stream.of(
                mockAnswersForProfessorAndCourseAndUser(professorId, courseId, new Random().nextLong(), 5, 2, 3, 4, 2),
                mockAnswersForProfessorAndCourseAndUser(professorId, courseId, new Random().nextLong(), 5, 2, 3, 1, 2),
                mockAnswersForProfessorAndCourseAndUser(professorId, courseId, new Random().nextLong(), 4, 3, 5, 1, 3)
        );
    }

    private Answer mockAnswersForProfessorAndCourseAndUser(Long professorId, Long courseId, Long userId,
                                                           Integer... marks) {
        Answer answer = new Answer();
        answer.setId(new Random().nextLong());
        answer.setProfessor(new Professor(professorId));
        answer.setUser(new User(userId));
        answer.setAnswers(mockAnswersMap(marks));
        answer.setCourse(new Course(courseId));
        return answer;
    }

    private AnswerDto mockAnswerDto(Integer... marks) {
        return new AnswerDto(mockAnswersMap(marks));
    }

    private Map<Integer, Integer> mockAnswersMap(Integer... marks) {
        List<Integer> marksAsList = Arrays.asList(marks);
        Map<Integer, Integer> result = new HashMap<>();
        result.put(0, marksAsList.get(0));
        result.put(1, marksAsList.get(1));
        result.put(2, marksAsList.get(2));
        result.put(3, marksAsList.get(3));
        result.put(4, marksAsList.get(4));
        return result;
    }
}