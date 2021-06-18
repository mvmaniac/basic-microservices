package io.devfactory.web.api;

import io.devfactory.web.dto.CatalogMapper;
import io.devfactory.web.dto.CatalogResponseView;
import io.devfactory.web.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/catalogs")
@RestController
public class CatalogApi {

  private final CatalogService catalogService;
  private final CatalogMapper catalogMapper;

  public CatalogApi(CatalogService catalogService, CatalogMapper catalogMapper) {
    this.catalogService = catalogService;
    this.catalogMapper = catalogMapper;
  }

  @GetMapping
  public ResponseEntity<List<CatalogResponseView>> retrieveCatalogs() {
    return ResponseEntity.ok(catalogMapper.toResponseViews(catalogService.findCatalogs()));
  }

}
