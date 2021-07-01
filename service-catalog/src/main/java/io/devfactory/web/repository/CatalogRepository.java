package io.devfactory.web.repository;

import io.devfactory.web.domain.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {

  Optional<Catalog> findByProductUniqueId(String productUniqueId);

}
