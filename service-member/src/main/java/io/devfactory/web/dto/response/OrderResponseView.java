package io.devfactory.web.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderResponseView {

  private final String orderId;
  private final String productId;
  private final int quantity;
  private final int unitPrice;
  private final int totalPrice;
  private final LocalDateTime createdDate;

  @Builder
  private OrderResponseView(String orderId, String productId, int quantity, int unitPrice,
      int totalPrice, LocalDateTime createdDate) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
    this.createdDate = createdDate;
  }

}
