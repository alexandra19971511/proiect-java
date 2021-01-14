package com.proiect.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proiect.model.User;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
  private Long id;
  @Setter
  private String password;
  private String fullName;
  private String email;
  private Date createdAt;
  private User.Role role;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.sss")
  public Date getCreatedAt() {
    if (this.createdAt == null) {
      return null;
    }
    return (Date) createdAt.clone();
  }

  public UserDto(Long id, String fullName, String email, User.Role role, Date createdAt) {
    this.id = id;
    this.fullName = fullName;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
  }
}
