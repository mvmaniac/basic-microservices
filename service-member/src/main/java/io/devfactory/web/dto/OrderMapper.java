package io.devfactory.web.dto;

import io.devfactory.web.dto.record.OrderRecord;
import io.devfactory.web.dto.response.OrderResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface OrderMapper {

  @Mapping(source = "orderUniqueId", target = "orderId")
  @Mapping(source = "productUniqueId", target = "productId")
  OrderResponseView recordToView(OrderRecord orderRecord);

  List<OrderResponseView> recordsToViews(List<OrderRecord> records);

}
