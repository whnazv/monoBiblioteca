package whnazv.biblioteca.application.port.in;

import whnazv.biblioteca.domain.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface BookUseCase {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, Book book);

    void deleteBook(Long id);

    boolean existsByIsbn(String isbn);

    String uploadBookImage(Long bookId, MultipartFile file) throws IOException;

    List<Book> searchBooks(String query);
}
