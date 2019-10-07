package io.devfactory.specialroute.repository;

import io.devfactory.specialroute.model.SpecialRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialRouteRepository extends JpaRepository<SpecialRoute, String> {
    Optional<SpecialRoute> findByServiceName(String serviceName);
}
