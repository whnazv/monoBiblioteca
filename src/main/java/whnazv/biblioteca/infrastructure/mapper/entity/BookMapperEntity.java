package whnazv.biblioteca.infrastructure.mapper.entity;

import whnazv.biblioteca.domain.model.Book;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapperEntity {

    Book toModel(BookEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    BookEntity toEntity(Book model);
}
