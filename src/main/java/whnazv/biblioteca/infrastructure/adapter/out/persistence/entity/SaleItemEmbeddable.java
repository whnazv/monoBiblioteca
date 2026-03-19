package whnazv.biblioteca.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import lombok.Setter;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Representa un ítem de venta como Value Object embebido en JPA.
 * No tiene ID propio porque pertenece completamente a SaleEntity.
 */
@Embeddable
@Setter
@Getter
@NoArgsConstructor
public class SaleItemEmbeddable {

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    public SaleItemEmbeddable(Long bookId, int quantity, BigDecimal unitPrice) {
        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
