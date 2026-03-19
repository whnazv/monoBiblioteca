package whnazv.biblioteca.infrastructure.mapper.dto;

import whnazv.biblioteca.domain.model.Book;
import whnazv.biblioteca.infrastructure.adapter.in.dto.BookDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapperDto {

    BookDto toDto(Book model);

    Book toDomain(BookDto dto);

    List<BookDto> toDtoList(List<Book> modelList);

    List<Book> toDomainList(List<BookDto> dtoList);
}
