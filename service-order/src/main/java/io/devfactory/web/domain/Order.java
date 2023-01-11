package io.devfactory.web.domain;

import io.devfactory.model.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Getter
@Table(name = "tb_order")
@Entity
public class Order extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String uniqueId;

  @Column(nullable = false, length = 120)
  private String productUniqueId;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = false)
  private int unitPrice;

  @Column(nullable = false)
  private int totalPrice;

  @Column(nullable = false)
  private String memberUniqueId;

  @Builder
  private Order(String uniqueId, String productUniqueId, int quantity, int unitPrice, int totalPrice,
      String memberUniqueId) {
    this.uniqueId = uniqueId;
    this.productUniqueId = productUniqueId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.totalPrice = totalPrice;
    this.memberUniqueId = memberUniqueId;
  }

  public void reception(String memberUniqueId) {
    this.uniqueId = UUID.randomUUID().toString();
    this.totalPrice = quantity * unitPrice;
    this.memberUniqueId = memberUniqueId;
  }

}
