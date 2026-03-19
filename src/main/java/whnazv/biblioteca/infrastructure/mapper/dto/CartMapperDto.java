package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.Cart;
import whnazv.biblioteca.infrastructure.adapter.in.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CartItemMapperDto.class})
public interface CartMapperDto {

    @Mapping(target = "total", expression = "java(model.getTotal())")
    CartDto toDto(Cart model);

    Cart toModel(CartDto dto);
}
