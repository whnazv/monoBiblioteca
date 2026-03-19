package whnazv.biblioteca.infrastructure.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.out.BookRepositoryPort;
import whnazv.biblioteca.domain.exception.NotFoundException;
import whnazv.biblioteca.domain.model.Book;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.entity.BookEntity;
import whnazv.biblioteca.infrastructure.adapter.out.persistence.repository.BookRepository;
import whnazv.biblioteca.infrastructure.mapper.entity.BookMapperEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {

    private final BookRepository repository;
    private final BookMapperEntity mapper;

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id).map(mapper::toModel);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = mapper.toEntity(book);
        return mapper.toModel(repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Libro no encontrado");
        }
        repository.deleteById(id);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return repository.existsByIsbn(isbn);
    }

    @Override
    public List<Book> searchByAnyField(String query) {
        return repository
                .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        query, query, query, query
                )
                .stream()
                .map(mapper::toModel)
                .collect(Collectors.toList());
    }
}
