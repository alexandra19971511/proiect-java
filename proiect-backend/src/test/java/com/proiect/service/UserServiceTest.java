package com.proiect.service;


import com.proiect.converter.UserToUserDtoConverter;
import com.proiect.dto.UserDto;
import com.proiect.model.User;
import com.proiect.repository.UserRepository;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private static final Long USER_ID = 1L;
    private static final String EMAIL = "ionm@gmail.com";
    private static final String PASSWORD = "hajdhajd";
    private static final String FULL_NAME = "Ion Cristian";
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder bcryptEncoder;
    @Spy
    private UserToUserDtoConverter converter;
    @InjectMocks
    private UserService sut;

    @Test
    public void loadUserByUsername_ShouldReturnUsername() {
        when(userRepository.findByEmail(EMAIL))
                .thenReturn(Optional.of(mockUser()));


        UserDetails result = sut.loadUserByUsername(EMAIL);

        UserDetails expectedResult = new org.springframework.security.core.userdetails.User(EMAIL, PASSWORD,
                Collections.singletonList(new SimpleGrantedAuthority(User.Role.ROLE_USER.name())));
        assertNotNull(result);
        assertEquals(expectedResult.getUsername(), result.getUsername());
        assertEquals(expectedResult.getPassword(), result.getPassword());
        assertEquals(expectedResult.getAuthorities(), result.getAuthorities());
    }

    @Test
    public void save_ShouldSaveAllUsers() {
        when(bcryptEncoder.encode(PASSWORD))
                .thenReturn("asasa");

        UserDto result = sut.save(mockUserDto());

        assertNotNull(result);
        UserDto expectedResult = mockUserDto();
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getRole(), result.getRole());
        assertEquals(expectedResult.getEmail(), result.getEmail());
    }

    @Test
    public void getUserInfo_ShouldReturnUserInfo() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(mockUser()));

        UserDto result = sut.getUserInfo(mockUserDto());

        assertNotNull(result);
        UserDto expectedResult = mockUserDto();
        assertEquals(expectedResult.getFullName(), result.getFullName());
        assertEquals(expectedResult.getRole(), result.getRole());
        assertEquals(expectedResult.getEmail(), result.getEmail());
    }

    private User mockUser() {
        return new User(USER_ID, EMAIL, PASSWORD, FULL_NAME);
    }

    private UserDto mockUserDto() {
        UserDto userDto = new UserDto(USER_ID, FULL_NAME, EMAIL, User.Role.ROLE_USER,
                new Date(System.currentTimeMillis()));
        userDto.setPassword(PASSWORD);
        return userDto;

    }
}