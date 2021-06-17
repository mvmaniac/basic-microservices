package io.devfactory.web.repository;

import io.devfactory.web.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findByUniqueId(String uniqueId);

  List<Order> findByMemberUniqueId(String memberUniqueId);

}
