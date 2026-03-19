package whnazv.biblioteca.infrastructure.mapper.entity;

import whnazv.biblioteca.domain.model.User;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperEntity {

    User toModel(UserEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(User model);
}
