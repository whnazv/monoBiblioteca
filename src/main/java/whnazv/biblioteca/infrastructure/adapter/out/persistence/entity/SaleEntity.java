package whnazv.biblioteca.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa una venta persistida.
 * Ahora almacena solo el ID del usuario para evitar dependencias innecesarias.
 */
@Entity
@Table(name = "sales")
@Setter
@Getter
@NoArgsConstructor
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ElementCollection
    @CollectionTable(
            name = "sale_items",
            joinColumns = @JoinColumn(name = "sale_id")
    )
    private List<SaleItemEmbeddable> items = new ArrayList<>();

    public SaleEntity(Long userId, List<SaleItemEmbeddable> items) {
        this.userId = userId;
        this.items = items;
        recalculateTotal();
    }

    public void addItem(SaleItemEmbeddable item) {
        this.items.add(item);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.total = items.stream()
                .map(i -> i.getUnitPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        recalculateTotal();
    }
}
