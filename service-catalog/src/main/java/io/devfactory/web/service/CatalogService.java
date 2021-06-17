package io.devfactory.web.service;

import io.devfactory.web.domain.Catalog;
import io.devfactory.web.repository.CatalogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CatalogService {

  private final CatalogRepository catalogRepository;

  public CatalogService(CatalogRepository catalogRepository) {
    this.catalogRepository = catalogRepository;
  }

  public List<Catalog> findCatalogs() {
    return catalogRepository.findAll();
  }

}
