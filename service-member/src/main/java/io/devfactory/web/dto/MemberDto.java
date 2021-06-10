package io.devfactory.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberDto {

  private final String userId;
  private final String email;
  private final String name;
  private final String password;

  @Builder
  private MemberDto(String userId, String email, String name, String password) {
    this.userId = userId;
    this.email = email;
    this.name = name;
    this.password = password;
  }

}
