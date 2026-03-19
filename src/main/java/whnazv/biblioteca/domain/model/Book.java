package whnazv.biblioteca.domain.model;

import lombok.Getter;
import lombok.Setter;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa un libro dentro del dominio.
 * Es una entidad con identidad propia y reglas de negocio internas.
 *
 * Invariantes:
 * - El título no puede ser nulo ni vacío.
 * - El autor no puede ser nulo ni vacío.
 * - El precio debe ser mayor que 0.
 * - El stock nunca puede ser negativo.
 *
 * Esta clase implementa un dominio rico: la lógica de negocio
 * relacionada con el libro vive dentro de la entidad.
 */
@Setter
@Getter
@NoArgsConstructor
public class Book {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
    private String description;

    /**
     * Crea un libro garantizando que cumple las reglas del dominio.
     */
    public Book(Long id,
                String title,
                String author,
                String isbn,
                BigDecimal price,
                int stock,
                String imageUrl,
                String description) {

        validateTitle(title);
        validateAuthor(author);
        validatePrice(price);
        validateStock(stock);

        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
    }


    /**
     * Reduce el stock del libro.
     * @throws IllegalArgumentException si la cantidad es inválida o no hay stock suficiente.
     */
    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        if (stock < quantity) {
            throw new IllegalArgumentException("Stock insuficiente para realizar la operación.");
        }
        this.stock -= quantity;
    }

    /**
     * Aumenta el stock del libro.
     */
    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        this.stock += quantity;
    }

    /**
     * Cambia el precio del libro.
     */
    public void changePrice(BigDecimal newPrice) {
        validatePrice(newPrice);
        this.price = newPrice;
    }

    private void validateTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
    }

    private void validateAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
    }

    private void validatePrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que 0.");
        }
    }

    private void validateStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
