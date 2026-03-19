package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserLoginDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLoginMapperDto {
    User toModel(UserLoginDto dto);
}
