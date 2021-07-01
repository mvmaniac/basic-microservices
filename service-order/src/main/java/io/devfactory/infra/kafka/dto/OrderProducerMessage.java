package io.devfactory.infra.kafka.dto;

import io.devfactory.infra.kafka.model.FieldModel;
import io.devfactory.infra.kafka.model.SchemaModel;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderProducerMessage {

  private static final List<FieldModel> fields = List.of(
      new FieldModel("string", true, "unique_id"),
      new FieldModel("string", true, "member_unique_id"),
      new FieldModel("string", true, "product_unique_id"),
      new FieldModel("int32", true, "quantity"),
      new FieldModel("int32", true, "unit_price"),
      new FieldModel("int32", true, "total_price")
  );

  private final SchemaModel schema;
  private final OrderProductPayload payload;

  private OrderProducerMessage(String topic, OrderProductPayload payload) {
    // TODO: 라이브러리를 활용하는 방법?
//    this.schema = SchemaBuilder.struct()
//        .name(topic)
//        .optional()
//        .field("order_unique_id", Schema.STRING_SCHEMA)
//        .field("product_unique_id", Schema.STRING_SCHEMA)
//        .field("quantity", Schema.INT32_SCHEMA)
//        .field("unit_price", Schema.INT32_SCHEMA)
//        .field("total_price", Schema.INT32_SCHEMA)
//        .build();

    this.schema = new SchemaModel("struct", fields, true, topic);
    this.payload = payload;
  }

  public static OrderProducerMessage of(String topic, OrderProductPayload payload) {
    return new OrderProducerMessage(topic, payload);
  }

}
