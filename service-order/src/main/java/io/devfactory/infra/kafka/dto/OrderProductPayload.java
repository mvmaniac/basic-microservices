package io.devfactory.infra.kafka.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.devfactory.web.dto.response.OrderResponseView;

public record OrderProductPayload(
    @JsonProperty("unique_id") String orderUniqueId,
    @JsonProperty("member_unique_id") String memberUniqueId,
    @JsonProperty("product_unique_id") String productUniqueId,
    @JsonProperty("quantity") int quantity,
    @JsonProperty("unit_price") int unitPrice,
    @JsonProperty("total_price") int totalPrice) {

  public static OrderProductPayload of(String memberUniqueId, OrderResponseView responseView) {
    return new OrderProductPayload(responseView.getOrderUniqueId(), memberUniqueId, responseView.getProductUniqueId(),
        responseView.getQuantity(), responseView.getUnitPrice(), responseView.getTotalPrice());
  }

}
