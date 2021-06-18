package io.devfactory.web.dto;

import io.devfactory.web.domain.Catalog;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
public interface CatalogMapper {

  CatalogResponseView toResponseView(Catalog catalog);

  List<CatalogResponseView> toResponseViews(List<Catalog> catalogs);

}
