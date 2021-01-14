package com.proiect.service;

import com.proiect.converter.UserToUserDtoConverter;
import com.proiect.dto.UserDto;
import com.proiect.model.User;
import com.proiect.repository.UserRepository;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@AllArgsConstructor
@Primary
public class UserService implements UserDetailsService {
  private UserRepository userRepository;
  private PasswordEncoder bcryptEncoder;
  private UserToUserDtoConverter converter;

  @Transactional
  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = findUserByEmail(email);

    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
  }

  private User findUserByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
  }

  public UserDto save(UserDto user) {
    if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) {
      throw new IllegalArgumentException("One of the required credentials is empty!");
    }

    String encodedPassword = bcryptEncoder.encode(user.getPassword());
    User newUser = new User(user.getEmail(), encodedPassword, user.getFullName());
    userRepository.save(newUser);
    return converter.convert(newUser);
  }

  public UserDto getUserInfo(UserDto userDto) {
    String email = userDto.getEmail();
    User user = findUserByEmail(email);
    return converter.convert(user);
  }
}
