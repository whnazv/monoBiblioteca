package whnazv.biblioteca.infrastructure.mapper.entity;

import whnazv.biblioteca.domain.model.Sale;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper entre SaleEntity (persistencia) y Sale (dominio).
 */
@Mapper(
        componentModel = "spring",
        uses = {SaleItemEmbeddableMapper.class}
)
public interface SaleMapperEntity {

    @Mapping(target = "userId", source = "userId")
    Sale toModel(SaleEntity entity);

    @Mapping(target = "userId", source = "userId")
    SaleEntity toEntity(Sale model);
}
