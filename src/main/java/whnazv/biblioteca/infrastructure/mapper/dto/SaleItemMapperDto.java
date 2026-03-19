package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.SaleItem;
import whnazv.biblioteca.infrastructure.adapter.in.dto.SaleItemDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleItemMapperDto {

    List<SaleItemDto> toDtoList(List<SaleItem> items);

    List<SaleItem> toDomainList(List<SaleItemDto> items);

    SaleItemDto toDto(SaleItem model);

    SaleItem toModel(SaleItemDto dto);
}
