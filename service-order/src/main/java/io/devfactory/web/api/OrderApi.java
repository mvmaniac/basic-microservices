package io.devfactory.web.api;

import io.devfactory.web.dto.OrderMapper;
import io.devfactory.web.dto.request.OrderRequestView;
import io.devfactory.web.dto.response.OrderResponseView;
import io.devfactory.web.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderApi {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  public OrderApi(OrderService orderService, OrderMapper orderMapper) {
    this.orderService = orderService;
    this.orderMapper = orderMapper;
  }

  @PostMapping("/{memberUniqueId}/orders")
  public ResponseEntity<OrderResponseView> createOrder(
      @PathVariable("memberUniqueId") String memberUniqueId,
      @RequestBody OrderRequestView requestView) {
    final var savedOrder = orderService.saveOrder(memberUniqueId, orderMapper.requestViewOf(requestView));
    return ResponseEntity.ok(orderMapper.toResponseView(savedOrder));
  }

  @GetMapping("/{memberUniqueId}/orders")
  public ResponseEntity<List<OrderResponseView>> retrieveOrders(
      @PathVariable("memberUniqueId") String memberUniqueId) {
    final var findOrders = orderService.findOrders(memberUniqueId);
    return ResponseEntity.ok(orderMapper.toResponseViews(findOrders));
  }

  @GetMapping("/orders/{uniqueId}")
  public ResponseEntity<OrderResponseView> retrieveOrder(
      @PathVariable("uniqueId") String uniqueId) {
    final var findOrder = orderService.findOrder(uniqueId);
    return ResponseEntity.ok(orderMapper.toResponseView(findOrder));
  }

}
