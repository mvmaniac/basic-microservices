package io.devfactory.web.service;

import io.devfactory.error.ServiceRuntimeException;
import io.devfactory.web.domain.Order;
import io.devfactory.web.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional
  public Order saveOrder(String memberUniqueId, Order order) {
    order.reception(memberUniqueId);
    return orderRepository.save(order);
  }

  public Order findOrder(String uniqueId) {
    return orderRepository.findByUniqueId(uniqueId)
        .orElseThrow(() -> new ServiceRuntimeException("Order not found."));
  }

  public List<Order> findOrders(String memberUniqueId) {
    return orderRepository.findByMemberUniqueId(memberUniqueId);
  }

}
