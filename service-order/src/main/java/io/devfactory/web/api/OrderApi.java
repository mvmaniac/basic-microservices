package io.devfactory.web.api;

import io.devfactory.infra.kafka.CatalogProducerService;
import io.devfactory.infra.kafka.OrderProducerService;
import io.devfactory.web.dto.OrderMapper;
import io.devfactory.web.dto.request.OrderRequestView;
import io.devfactory.web.dto.response.OrderResponseView;
import io.devfactory.web.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
public class OrderApi {

  private final OrderService orderService;
  private final OrderMapper orderMapper;

  private final CatalogProducerService catalogProducerService;
  private final OrderProducerService orderProducerService;

  public OrderApi(OrderService orderService, OrderMapper orderMapper,
      CatalogProducerService catalogProducerService,
      OrderProducerService orderProducerService) {
    this.orderService = orderService;
    this.orderMapper = orderMapper;
    this.catalogProducerService = catalogProducerService;
    this.orderProducerService = orderProducerService;
  }

  @PostMapping("/{memberUniqueId}/orders")
  public ResponseEntity<OrderResponseView> createOrder(
      @PathVariable("memberUniqueId") String memberUniqueId,
      @RequestBody OrderRequestView requestView) {

    log.info("Before add order data");

    // use jpa
    // final var savedOrder = orderService.saveOrder(memberUniqueId, orderMapper.requestViewOf(requestView));
    // final var orderResponseView = orderMapper.toResponseView(savedOrder);

    // use kafka
    final var buildOrder = orderMapper.requestViewOf(requestView);
    buildOrder.reception(memberUniqueId);

    final var orderResponseView = orderMapper.toResponseView(buildOrder);

    // send this buildOrder to the kafka
    catalogProducerService.send("catalog-consumer-topic", orderResponseView);
    orderProducerService.send("tb_order", memberUniqueId, orderResponseView);

    return ResponseEntity.status(CREATED).body(orderResponseView);
  }

  @GetMapping("/{memberUniqueId}/orders")
  public ResponseEntity<List<OrderResponseView>> retrieveOrders(
      @PathVariable("memberUniqueId") String memberUniqueId) {

    log.info("Before retrieve order data");
    final var findOrders = orderService.findOrders(memberUniqueId);
    log.info("Before retrieve order data");

    return ResponseEntity.ok(orderMapper.toResponseViews(findOrders));
  }

  @GetMapping("/orders/{uniqueId}")
  public ResponseEntity<OrderResponseView> retrieveOrder(
      @PathVariable("uniqueId") String uniqueId) {
    final var findOrder = orderService.findOrder(uniqueId);
    return ResponseEntity.ok(orderMapper.toResponseView(findOrder));
  }

}
