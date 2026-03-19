package whnazv.biblioteca.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Sale {

    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
    private List<SaleItem> items = new ArrayList<>();
    private BigDecimal total = BigDecimal.ZERO;

    public Sale(Long id, Long userId, List<SaleItem> items) {
        if (userId == null) throw new IllegalArgumentException("La venta debe tener un usuario.");
        if (items == null || items.isEmpty()) throw new IllegalArgumentException("La venta debe tener al menos un ítem.");

        this.id = id;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
        this.items = new ArrayList<>(items);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.total = items.stream()
                .map(SaleItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
