package io.devfactory.web.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderRequestView {

  private final String productUniqueId;
  private final int quantity;
  private final int unitPrice;

  @Builder
  private OrderRequestView(String productUniqueId, int quantity, int unitPrice) {
    this.productUniqueId = productUniqueId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
  }

}
