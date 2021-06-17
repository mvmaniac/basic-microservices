package io.devfactory.web.dto;

public record CatalogRecord(
    String productId, int quantity, int unitPrice, int totalPrice,
    String orderUniqueId, String memberUniqueId) {

}
