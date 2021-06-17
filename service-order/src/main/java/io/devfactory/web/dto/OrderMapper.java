package io.devfactory.web.dto;

import io.devfactory.web.domain.Order;
import io.devfactory.web.dto.request.OrderRequestView;
import io.devfactory.web.dto.response.OrderResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  Order requestViewOf(OrderRequestView requestView);

  @Mapping(source = "uniqueId", target = "orderUniqueId")
  OrderResponseView toResponseView(Order order);

  List<OrderResponseView> toResponseViews(List<Order> orders);

}
