package io.devfactory.web.dto;

import io.devfactory.web.domain.Order;
import io.devfactory.web.dto.request.OrderRequestView;
import io.devfactory.web.dto.response.OrderResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface OrderMapper {

  @Mapping(target = "uniqueId", ignore = true)
  @Mapping(target = "totalPrice", ignore = true)
  @Mapping(target = "memberUniqueId", ignore = true)
  Order requestViewOf(OrderRequestView requestView);

  @Mapping(source = "uniqueId", target = "orderUniqueId")
  OrderResponseView toResponseView(Order order);

  List<OrderResponseView> toResponseViews(List<Order> orders);

}
