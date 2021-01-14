package com.proiect.service;


import com.proiect.converter.CourseToCourseDtoConverter;
import com.proiect.dto.CourseDto;
import com.proiect.model.Course;
import com.proiect.model.Professor;
import com.proiect.repository.CourseRepository;
import com.proiect.repository.ProfessorRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    private static final Long PROFESSOR_ID = 1L;
    private static final Long COURSE_ID = 1L;

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ProfessorRepository professorRepository;
    @Spy
    private CourseToCourseDtoConverter courseToCourseDtoConverter;
    @InjectMocks
    private CourseService sut;

    @Test
    public void getAll_ShouldReturnAllCourses() {
        when(courseRepository.findAll())
                .thenReturn(Arrays.asList(mockCourse()));

        CourseDto result = sut.getAll().stream().collect(Collectors.toList()).get(0);

        assertNotNull(result);
        CourseDto expectedResult = mockCourseDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getProfessorId(), result.getProfessorId());
    }

    private Course mockCourse() {
        Course course = new Course(COURSE_ID, "Mathematics");
        course.setProfessor(new Professor(PROFESSOR_ID, "Ion Andrei"));
        return course;
    }

    private CourseDto mockCourseDto() {
        return new CourseDto(COURSE_ID, "Mathematics", PROFESSOR_ID);
    }

    @Test
    public void getAllByProfessorId_ShouldReturnAllCoursesByProfessorId() {
        when(professorRepository.findOne(PROFESSOR_ID))
                .thenReturn(mockProfessor());
        when(courseRepository.findByProfessorId(PROFESSOR_ID))
                .thenReturn(Stream.of(mockCourse()));

        CourseDto result = sut.getAllByProfessorId(PROFESSOR_ID).stream().collect(Collectors.toList()).get(0);

        assertNotNull(result);
        CourseDto expectedResult = mockCourseDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getProfessorId(), result.getProfessorId());
    }

    @Test
    public void getById() {
        when(professorRepository.findOne(PROFESSOR_ID))
                .thenReturn(mockProfessor());
        when(courseRepository.findByIdAndProfessorId(COURSE_ID, PROFESSOR_ID))
                .thenReturn(Optional.of(mockCourse()));

        CourseDto result = sut.getById(COURSE_ID, PROFESSOR_ID);

        assertNotNull(result);
        CourseDto expectedResult = mockCourseDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getProfessorId(), result.getProfessorId());
    }

    private Professor mockProfessor() {
        return new Professor(PROFESSOR_ID, "Mihai Cristea");
    }

    @Test
    public void save_ShouldSaveCourse() {
        when(courseRepository.save(any(Course.class)))
                .thenReturn(mockCourse());

        CourseDto result = sut.save(PROFESSOR_ID, mockCourseDto());

        assertNotNull(result);
        CourseDto expectedResult = mockCourseDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getName(), result.getName());
        assertEquals(expectedResult.getProfessorId(), result.getProfessorId());
    }

    @Test
    public void delete_ShouldDeleteCourse() {
        when(courseRepository.findOne(COURSE_ID))
                .thenReturn(mockCourse());

        sut.delete(COURSE_ID);

        verify(courseRepository).delete(COURSE_ID);
    }

}