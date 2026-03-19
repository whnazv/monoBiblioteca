package whnazv.biblioteca.domain.model;

import java.math.BigDecimal;

/**
 * Value Object que representa un ítem dentro de una venta.
 * Es completamente inmutable y no tiene identidad propia.
 *
 * Invariantes:
 * - bookId no puede ser nulo.
 * - quantity debe ser mayor que 0.
 * - unitPrice debe ser mayor que 0.
 */
public final class SaleItem {

    private final Long bookId;
    private final int quantity;
    private final BigDecimal unitPrice;

    /**
     * Crea un ítem de venta garantizando que cumple las reglas del dominio.
     */
    public SaleItem(Long bookId, int quantity, BigDecimal unitPrice) {
        if (bookId == null) {
            throw new IllegalArgumentException("El ID del libro no puede ser nulo.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser mayor que 0.");
        }

        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getBookId() {
        return bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    /**
     * Devuelve el total del ítem (precio * cantidad).
     */
    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
