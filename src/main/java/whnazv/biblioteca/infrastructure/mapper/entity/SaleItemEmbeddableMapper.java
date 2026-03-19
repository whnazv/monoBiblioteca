package whnazv.biblioteca.infrastructure.mapper.entity;

import whnazv.biblioteca.domain.model.SaleItem;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.SaleItemEmbeddable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleItemEmbeddableMapper {

    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitPrice", source = "unitPrice")
    SaleItem toModel(SaleItemEmbeddable embeddable);

    @Mapping(target = "bookId", source = "bookId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "unitPrice", source = "unitPrice")
    SaleItemEmbeddable toEntity(SaleItem model);
}
