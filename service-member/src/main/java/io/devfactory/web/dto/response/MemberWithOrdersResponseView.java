package io.devfactory.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberWithOrdersResponseView {

  // TODO: MemberResponseView를 상속 받아서 할 수 있는 방법?
  private final String uniqueId;
  private final String email;
  private final String name;
  private final List<OrderResponseView> orders;

  @Builder
  private MemberWithOrdersResponseView(String uniqueId, String email, String name, List<OrderResponseView> orders) {
    this.uniqueId = uniqueId;
    this.email = email;
    this.name = name;
    this.orders = orders;
  }

}
