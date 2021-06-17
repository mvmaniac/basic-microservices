package io.devfactory.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CatalogResponseView {

  private final String productUniqueId;
  private final String productName;
  private final int stock;
  private final int unitPrice;
  private final LocalDateTime createdDate;

  @Builder
  private CatalogResponseView(String productUniqueId, String productName, int stock, int unitPrice,
      LocalDateTime createdDate) {
    this.productUniqueId = productUniqueId;
    this.productName = productName;
    this.stock = stock;
    this.unitPrice = unitPrice;
    this.createdDate = createdDate;
  }

}
