package io.devfactory.web.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberResponseView {

  private final String uniqueId;
  private final String email;
  private final String name;

  @Builder
  protected MemberResponseView(String uniqueId, String email, String name) {
    this.uniqueId = uniqueId;
    this.email = email;
    this.name = name;
  }

}
