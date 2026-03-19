package whnazv.biblioteca.application.usecase;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.BookUseCase;
import whnazv.biblioteca.application.port.out.BookRepositoryPort;
import whnazv.biblioteca.application.port.out.ImageStoragePort;
import whnazv.biblioteca.domain.exception.NotFoundException;
import whnazv.biblioteca.domain.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService implements BookUseCase {

    private final BookRepositoryPort bookRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    @Override
    public List<Book> getAllBooks() {
        return bookRepositoryPort.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepositoryPort.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepositoryPort.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existing = bookRepositoryPort.findById(id);
        if (existing.isEmpty()) {
            throw new NotFoundException("Libro no encontrado");
        }
        updatedBook.setId(id);
        return bookRepositoryPort.save(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> existing = bookRepositoryPort.findById(id);
        if (existing.isEmpty()) {
            throw new NotFoundException("Libro no encontrado");
        }
        bookRepositoryPort.deleteById(id);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepositoryPort.existsByIsbn(isbn);
    }

    @Override
    public String uploadBookImage(Long bookId, MultipartFile file) throws IOException {
        Optional<Book> bookOpt = bookRepositoryPort.findById(bookId);
        if (bookOpt.isEmpty()) {
            throw new NotFoundException("Libro no encontrado");
        }
        String imageUrl = imageStoragePort.storeImage(file);
        Book book = bookOpt.get();
        book.setImageUrl(imageUrl);
        bookRepositoryPort.save(book);
        return imageUrl;
    }

    @Override
    public List<Book> searchBooks(String query) {
        return bookRepositoryPort.searchByAnyField(query);
    }
}
