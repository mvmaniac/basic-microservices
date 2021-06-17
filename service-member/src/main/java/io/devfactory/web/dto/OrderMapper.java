package io.devfactory.web.dto;

import io.devfactory.web.dto.record.OrderRecord;
import io.devfactory.web.dto.response.OrderResponseView;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

  OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

  OrderResponseView recordToView(OrderRecord orderRecord);

  List<OrderResponseView> recordsToViews(List<OrderRecord> records);

}
