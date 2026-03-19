package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.CartItem;
import whnazv.biblioteca.infrastructure.adapter.in.dto.CartItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartItemMapperDto {

    CartItemDto toDto(CartItem model);

    CartItem toModel(CartItemDto dto);
}
