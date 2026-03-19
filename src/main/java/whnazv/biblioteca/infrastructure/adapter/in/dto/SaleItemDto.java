package whnazv.biblioteca.infrastructure.adapter.in.dto;

import java.math.BigDecimal;

public record SaleItemDto(
        Long bookId,
        int quantity,
        BigDecimal unitPrice
) {}
