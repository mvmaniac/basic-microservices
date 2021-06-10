package io.devfactory.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseView {

  private final String userId;
  private final String email;
  private final String name;

  @Builder
  private MemberResponseView(String userId, String email, String name) {
    this.userId = userId;
    this.email = email;
    this.name = name;
  }

}
