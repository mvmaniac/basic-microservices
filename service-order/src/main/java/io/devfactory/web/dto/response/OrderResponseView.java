package io.devfactory.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseView {

  private final String orderUniqueId;
  private final String productUniqueId;
  private final int quantity;
  private final int unitPrice;
  private final int totalPrice;
  private final LocalDateTime createdDate;

  @Builder
  private OrderResponseView(String orderUniqueId, String productUniqueId, int quantity, int unitPrice,
      int totalPrice, LocalDateTime createdDate) {
    this.orderUniqueId = orderUniqueId;
    this.productUniqueId = productUniqueId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
    this.createdDate = createdDate;
  }

}
