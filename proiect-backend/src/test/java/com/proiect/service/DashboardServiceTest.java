package com.proiect.service;

import com.proiect.dto.CourseDto;
import com.proiect.dto.ProfessorDto;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DashboardServiceTest {
    private static final String PROFESSOR_NAME = "Dima Diana";
    private static final Long PROFESSOR_ID = 1L;
    private static final Long COURSE_ID = 1L;
    private CourseDto courseDto;

    @Mock
    private CourseService courseService;
    @Mock
    private ProfessorService professorService;

    @InjectMocks
    private DashboardService sut;

    @Before
    public void setup() {
        courseDto = mockCourseDto();
    }

    @Test
    public void save_ShouldSaveProfessorCourse() {
        when(professorService.getByFullName(PROFESSOR_NAME))
                .thenReturn(mockProfessorDto());
        when(courseService.save(PROFESSOR_ID, courseDto))
                .thenReturn(courseDto);

        ProfessorDto result = sut.save(mockProfessorDto());

        assertEquals(PROFESSOR_ID, result.getId());
        assertEquals(PROFESSOR_NAME, result.getFullName());
        assertNotNull(result.getCourseDto());
        assertEquals(COURSE_ID, result.getCourseDto().getId());
    }

    private CourseDto mockCourseDto() {
        return new CourseDto(COURSE_ID, "Mathematics", PROFESSOR_ID);
    }

    private ProfessorDto mockProfessorDto() {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(1l);
        professorDto.setFullName(PROFESSOR_NAME);
        professorDto.setCourseDto(courseDto);
        return professorDto;
    }

    @Test
    public void delete_ShouldDeleteProfessorWhenNoCourse() {
        sut.delete(PROFESSOR_ID, COURSE_ID);

        verify(courseService).delete(COURSE_ID);
        verify(professorService).delete(PROFESSOR_ID);
    }

    @Test
    public void getAll_ShouldReturnAllProfessorCourses() {
        when(courseService.getAll())
                .thenReturn(Arrays.asList(courseDto));
        when(professorService.getById(PROFESSOR_ID))
                .thenReturn(mockProfessorDto());

        ProfessorDto result = new ArrayList<>(sut.getAll()).get(0);

        assertEquals(PROFESSOR_ID, result.getId());
        assertEquals(PROFESSOR_NAME, result.getFullName());
        assertEquals(COURSE_ID, result.getCourseDto().getId());
    }
}