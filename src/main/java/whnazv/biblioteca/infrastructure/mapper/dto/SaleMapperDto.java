package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.Sale;
import whnazv.biblioteca.domain.model.SaleItem;
import whnazv.biblioteca.infrastructure.adapter.in.dto.SaleDto;
import whnazv.biblioteca.infrastructure.adapter.in.dto.SaleItemDto;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Mapper encargado de convertir entre entidades del dominio {@link Sale}
 * y sus representaciones DTO {@link SaleDto}.
 */
@Component
public class SaleMapperDto {

    private final SaleItemMapperDto saleItemMapperDto;

    public SaleMapperDto(SaleItemMapperDto saleItemMapperDto) {
        this.saleItemMapperDto = saleItemMapperDto;
    }

    /**
     * Convierte un modelo de dominio {@link Sale} a su DTO {@link SaleDto}.
     */
    public SaleDto toDto(Sale model) {
        List<SaleItemDto> itemsDto = saleItemMapperDto.toDtoList(model.getItems());
        return new SaleDto(model.getUserId(), itemsDto);
    }

    /**
     * Convierte un DTO {@link SaleDto} a su modelo de dominio {@link Sale}.
     */
    public Sale toModel(SaleDto dto) {
        return new Sale(
                null,
                dto.userId(),
                saleItemMapperDto.toDomainList(dto.items())
        );
    }

    /**
     * Convierte una lista de DTOs de items a una lista de modelos de dominio.
     */
    public List<SaleItem> toDomainList(List<SaleItemDto> items) {
        return saleItemMapperDto.toDomainList(items);
    }
}
