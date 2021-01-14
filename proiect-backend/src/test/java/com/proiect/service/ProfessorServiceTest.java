package com.proiect.service;


import com.proiect.converter.ProfessorToProfessorDtoConverter;
import com.proiect.dto.ProfessorDto;
import com.proiect.model.Professor;
import com.proiect.repository.ProfessorRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

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
public class ProfessorServiceTest {
    private static final Long PROFESSOR_ID = 1L;
    public static final String FULL_NAME = "Dima Ana";

    @Mock
    private ProfessorRepository professorRepository;
    @Spy
    private ProfessorToProfessorDtoConverter converter;

    @InjectMocks
    private ProfessorService sut;

    @Test
    public void getAll_ShouldReturnAllProfessors() {
        when(professorRepository.findAll())
                .thenReturn(Collections.singletonList(mockProfessor()));

        ProfessorDto result = new ArrayList<>(sut.getAll()).get(0);

        assertNotNull(result);
        ProfessorDto expectedResult = mockProfessorDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getCourseDto(), result.getCourseDto());

    }

    private Professor mockProfessor() {

        return new Professor(PROFESSOR_ID, FULL_NAME);
    }

    private ProfessorDto mockProfessorDto() {
        ProfessorDto professorDto = new ProfessorDto();
        professorDto.setId(PROFESSOR_ID);
        professorDto.setFullName(FULL_NAME);
        return professorDto;
    }

    @Test
    public void getByFullName_ShouldReturnProfessorsFullName() {
        when(professorRepository.findByFullName(FULL_NAME))
                .thenReturn(Optional.of(mockProfessor()));

        ProfessorDto result = sut.getByFullName(FULL_NAME);

        assertNotNull(result);
        ProfessorDto expectedResult = mockProfessorDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getCourseDto(), result.getCourseDto());
    }

    @Test
    public void getById_ShouldReturnProfessorsId() {
        when(professorRepository.findOne(PROFESSOR_ID))
                .thenReturn(mockProfessor());

        ProfessorDto result = sut.getById(PROFESSOR_ID);

        assertNotNull(result);
        ProfessorDto expectedResult = mockProfessorDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getCourseDto(), result.getCourseDto());
    }

    @Test
    public void save_ShouldSaveProfessor() {
        when(professorRepository.save(any(Professor.class)))
                .thenReturn(mockProfessor());

        ProfessorDto result = sut.save(mockProfessorDto());

        assertNotNull(result);
        ProfessorDto expectedResult = mockProfessorDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getCourseDto(), result.getCourseDto());
    }

    @Test
    public void delete_ShouldDeleteProfessor() {
        when(professorRepository.findOne(PROFESSOR_ID))
                .thenReturn(mockProfessor());

        sut.delete(PROFESSOR_ID);

        verify(professorRepository).delete(PROFESSOR_ID);
    }
}