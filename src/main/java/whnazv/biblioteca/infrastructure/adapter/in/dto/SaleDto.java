package whnazv.biblioteca.infrastructure.adapter.in.dto;

import java.util.List;

/**
 * DTO que representa una venta expuesta al exterior.
 * Solo contiene el ID del usuario para evitar exponer datos sensibles.
 */
public record SaleDto(
        Long userId,
        List<SaleItemDto> items
) {}
