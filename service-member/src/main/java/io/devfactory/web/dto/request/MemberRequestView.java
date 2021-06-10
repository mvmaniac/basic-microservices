package io.devfactory.web.dto.request;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class MemberRequestView {

  @NotNull(message = "Email cannot be null")
  @Size(min = 2, message = "Email not be less then 2 characters")
  private final String email;

  @NotNull(message = "Name cannot be null")
  @Size(min = 2, message = "Name not be less then 2 characters")
  private final String name;

  @NotNull(message = "Password cannot be null")
  @Size(min = 8, message = "Password must be equal or greater then 8 characters")
  private final String password;

  @Builder
  private MemberRequestView(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

}
