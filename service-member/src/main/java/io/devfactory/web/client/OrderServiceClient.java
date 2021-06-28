package io.devfactory.web.client;

import io.devfactory.web.dto.record.OrderRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "service-order")
public interface OrderServiceClient {

  @GetMapping("/{memberUniqueId}/orders")
  List<OrderRecord> retrieveOrders(@PathVariable("memberUniqueId") String memberUniqueId);

}
