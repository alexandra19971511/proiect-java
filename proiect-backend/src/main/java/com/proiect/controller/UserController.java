package com.proiect.controller;


import com.proiect.dto.AuthenticationRequest;
import com.proiect.dto.AuthenticationResponse;
import com.proiect.dto.TokenRequestDto;
import com.proiect.dto.UserDto;
import com.proiect.security.JwtTokenUtil;
import com.proiect.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@AllArgsConstructor
public class UserController {
  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserService service;

  @PostMapping(value = "/authenticate")
  public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
      throws Exception {
    authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

    final UserDetails userDetails = service.loadUserByUsername(authenticationRequest.getEmail());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return new AuthenticationResponse(token);
  }

  private void authenticate(String email, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }

  @PostMapping(value = "/register")
  public UserDto save(@RequestBody UserDto user) {
    return service.save(user);
  }

  @PostMapping(value = "/user-info")
  public UserDto getUserInfo(@RequestBody UserDto user) {
    return service.getUserInfo(user);
  }

  @PostMapping(value = "/validate-token")
  public Boolean validateToken(@RequestBody TokenRequestDto tokenRequest) {
    return !jwtTokenUtil.isTokenExpired(tokenRequest.getToken());
  }

}