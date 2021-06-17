package io.devfactory.web.dto.record;

import java.time.LocalDateTime;

public record OrderRecord(
    String orderUniqueId, String productUniqueId, int quantity, int unitPrice,
    int totalPrice, LocalDateTime createdDate) {

}
