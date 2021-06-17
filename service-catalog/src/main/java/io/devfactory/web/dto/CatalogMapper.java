package io.devfactory.web.dto;

import io.devfactory.web.domain.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

  CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

  CatalogResponseView toResponseView(Catalog catalog);
  List<CatalogResponseView> toResponseViews(List<Catalog> catalogs);

}
