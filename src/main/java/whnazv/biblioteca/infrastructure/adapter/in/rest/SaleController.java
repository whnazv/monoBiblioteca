package whnazv.biblioteca.infrastructure.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.SaleUseCase;
import whnazv.biblioteca.infrastructure.adapter.in.dto.SaleDto;
import whnazv.biblioteca.infrastructure.mapper.dto.SaleMapperDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleUseCase saleUseCase;
    private final SaleMapperDto saleMapperDto;

    /**
     * Crea una venta a partir de un DTO.
     *
     * @param dto datos de la venta
     * @return venta creada en formato DTO
     */
    @PostMapping
    public SaleDto createSale(@RequestBody SaleDto dto) {

        var model = saleMapperDto.toModel(dto);

        var sale = saleUseCase.createSale(
                model.getUserId(),
                model.getItems()
        );

        return saleMapperDto.toDto(sale);
    }
}
