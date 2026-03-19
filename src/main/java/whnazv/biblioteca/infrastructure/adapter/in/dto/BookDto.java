package whnazv.biblioteca.infrastructure.adapter.in.dto;

import java.math.BigDecimal;

public record BookDto(
        Long id,
        String title,
        String author,
        String isbn,
        BigDecimal price,
        int stock,
        String imageUrl,
        String description
) {}
