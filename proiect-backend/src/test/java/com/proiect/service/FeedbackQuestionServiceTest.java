package com.proiect.service;


import com.proiect.converter.FeedbackQuestionToFeedbackQuestionDtoConverter;
import com.proiect.dto.FeedbackQuestionDto;
import com.proiect.model.FeedbackQuestion;
import com.proiect.repository.FeedbackQuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

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
public class FeedbackQuestionServiceTest {

    public static final long FEEDBACK_ID = 1l;
    @Mock
    private FeedbackQuestionRepository feedbackQuestionRepository;
    @Spy
    private FeedbackQuestionToFeedbackQuestionDtoConverter converter;

    @InjectMocks
    private FeedbackQuestionService sut;

    @Test
    public void getAll_ShouldReturnAllFeedback() {
        when(feedbackQuestionRepository.findAll())
                .thenReturn(Collections.singletonList(mockFeedbackQuestion()));

        FeedbackQuestionDto result = new ArrayList<>(sut.getAll()).get(0);

        assertNotNull(result);
        FeedbackQuestionDto expectedResult = mockFeedbackQuestionDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getQuestion(), result.getQuestion());
    }

    @Test
    public void getById_ShouldReturnFeedbackById() {
        when(feedbackQuestionRepository.findOne(FEEDBACK_ID))
                .thenReturn(mockFeedbackQuestion());

        FeedbackQuestionDto result = sut.getById(FEEDBACK_ID);

        assertNotNull(result);
        FeedbackQuestionDto expectedResult = mockFeedbackQuestionDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getQuestion(), result.getQuestion());
    }

    @Test
    public void save_ShouldSaveFeedback() {
        when(feedbackQuestionRepository.save(any(FeedbackQuestion.class)))
                .thenReturn(mockFeedbackQuestion());

        FeedbackQuestionDto result = sut.save(mockFeedbackQuestionDto());

        assertNotNull(result);
        FeedbackQuestionDto expectedResult = mockFeedbackQuestionDto();
        assertEquals(expectedResult.getId(), result.getId());
        assertEquals(expectedResult.getQuestion(), result.getQuestion());
    }

    private FeedbackQuestion mockFeedbackQuestion() {
        return new FeedbackQuestion(FEEDBACK_ID, "HOW?");
    }

    private FeedbackQuestionDto mockFeedbackQuestionDto() {
        return new FeedbackQuestionDto(FEEDBACK_ID, "HOW?");
    }

    @Test
    public void delete_ShouldDeleteFeedback() {
        when(feedbackQuestionRepository.findOne(FEEDBACK_ID))
                .thenReturn(mockFeedbackQuestion());

        sut.delete(FEEDBACK_ID);

        verify(feedbackQuestionRepository).delete(FEEDBACK_ID);
    }
}