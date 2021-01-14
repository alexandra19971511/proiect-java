package com.proiect.converter;

import com.proiect.dto.UserDto;
import com.proiect.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDtoConverter {
    public UserDto convert(User source) {
        UserDto result = new UserDto(source.getId(), source.getFullName(), source.getEmail(),
                User.Role.valueOf(source.getRole()), source.getCreatedAt());
        return result;
    }
}