package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.in.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperDto {
    
    public UserResponse toResponse(User user);

}
