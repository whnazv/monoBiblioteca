package whnazv.biblioteca.infrastructure.adapter.in.dto;

import java.math.BigDecimal;

public record CartItemDto(
        Long bookId,
        String title,
        BigDecimal price,
        int quantity
) {}
