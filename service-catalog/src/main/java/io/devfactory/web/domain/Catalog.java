package io.devfactory.web.domain;

import io.devfactory.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_catalog")
@Entity
public class Catalog extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false, length = 120, unique = true)
  private String productUniqueId;

  @Column(nullable = false)
  private String productName;

  @Column(nullable = false)
  private int stock;

  @Column(nullable = false)
  private int unitPrice;

  @Builder
  private Catalog(String productUniqueId, String productName, int stock, int unitPrice) {
    this.productUniqueId = productUniqueId;
    this.productName = productName;
    this.stock = stock;
    this.unitPrice = unitPrice;
  }

  public void updateProductUniqueId(String productUniqueId) {
    this.productUniqueId = productUniqueId;
  }

  public void updateStock(int quantity) {
    stock = stock - quantity;
  }

}
