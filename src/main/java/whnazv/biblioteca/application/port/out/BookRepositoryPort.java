package whnazv.biblioteca.application.port.out;

import whnazv.biblioteca.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {

    Optional<Book> findById(Long id);

    List<Book> findAll();

    Book save(Book book);

    void deleteById(Long id);

    boolean existsByIsbn(String isbn);

    List<Book> searchByAnyField(String query);
}
