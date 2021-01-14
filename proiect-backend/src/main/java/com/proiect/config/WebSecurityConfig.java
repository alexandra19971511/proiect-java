package com.proiect.config;

import com.proiect.security.JwtAuthenticationEntryPoint;
import com.proiect.security.JwtRequestFilter;
import com.proiect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  @Autowired
  private UserService jwtUserDetailsService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
    auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  public JwtRequestFilter getJwtRequestFilter() {
    return new JwtRequestFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
// We don't need CSRF for this example
    httpSecurity
        .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
        .and()
        .authorizeRequests().antMatchers("/authenticate", "/register", "/validate-token").permitAll()
        // all other requests need to be authenticated
        .anyRequest().authenticated()
        // make sure we use stateless session; session won't be used to
// store user's state.
        .and()
        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .cors();
// Add a filter to validate the tokens with every request
    httpSecurity.addFilterBefore(getJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
  }
}