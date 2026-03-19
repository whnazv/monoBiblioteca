package whnazv.biblioteca.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.BookUseCase;
import whnazv.biblioteca.infrastructure.adapter.in.dto.BookDto;
import whnazv.biblioteca.infrastructure.mapper.dto.BookMapperDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Controlador REST para gestionar operaciones relacionadas con libros.
 * Expone endpoints para CRUD y subida de imágenes.
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookUseCase bookUseCase;
    private final BookMapperDto bookMapperDto;

    /**
     * Obtiene todos los libros disponibles.
     *
     * @return lista de libros en formato DTO
     */
    @GetMapping
    public List<BookDto> getAll() {
        return bookMapperDto.toDtoList(bookUseCase.getAllBooks());
    }

    /**
     * Obtiene un libro por su ID.
     *
     * @param id identificador del libro
     * @return libro encontrado en formato DTO
     */
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookUseCase.getBookById(id)
                .map(bookMapperDto::toDto)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    /**
     * Crea un nuevo libro a partir de un DTO.
     *
     * @param dto datos del libro
     * @return libro creado en formato DTO
     */
    @PostMapping
    public BookDto create(@RequestBody BookDto dto) {
        var saved = bookUseCase.saveBook(bookMapperDto.toDomain(dto));
        return bookMapperDto.toDto(saved);
    }

    /**
     * Actualiza un libro existente.
     *
     * @param id  identificador del libro
     * @param dto datos actualizados
     * @return libro actualizado en formato DTO
     */
    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody BookDto dto) {
        var updated = bookUseCase.updateBook(id, bookMapperDto.toDomain(dto));
        return bookMapperDto.toDto(updated);
    }

    /**
     * Elimina un libro por su ID.
     *
     * @param id identificador del libro
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookUseCase.deleteBook(id);
    }

    /**
     * Sube una imagen asociada a un libro.
     *
     * @param id   identificador del libro
     * @param file archivo de imagen
     * @return URL de la imagen subida
     */
    @PostMapping("/{id}/image")
    public String uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            return bookUseCase.uploadBookImage(id, file);
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen", e);
        }
    }
}
