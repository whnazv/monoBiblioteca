package whnazv.biblioteca.application.port.in;

import whnazv.biblioteca.domain.model.Sale;
import whnazv.biblioteca.domain.model.SaleItem;

import java.util.List;

/**
 * Puerto de entrada para gestionar las operaciones relacionadas con ventas.
 * Define las acciones que pueden realizarse desde la capa de aplicación.
 */
public interface SaleUseCase {

    /**
     * Crea una nueva venta.
     *
     * @param userId ID del usuario que realiza la venta
     * @param items lista de ítems incluidos en la venta
     * @return la venta creada
     */
    Sale createSale(Long userId, List<SaleItem> items);

    /**
     * Obtiene todas las ventas registradas.
     *
     * @return lista completa de ventas
     */
    List<Sale> getAllSales();

    /**
     * Obtiene todas las ventas asociadas a un usuario.
     *
     * @param userId ID del usuario
     * @return lista de ventas del usuario
     */
    List<Sale> getSalesByUserId(Long userId);

    /**
     * Obtiene una venta por su ID.
     *
     * @param saleId ID de la venta
     * @return la venta encontrada o null si no existe
     */
    Sale getSaleById(Long saleId);

    /**
     * Elimina una venta por su ID.
     *
     * @param saleId ID de la venta
     * @return true si la venta fue eliminada, false si no existía
     */
    boolean deleteSale(Long saleId);

    /**
     * Actualiza una venta existente.
     *
     * @param saleId ID de la venta a actualizar
     * @param userId ID del usuario asociado a la venta
     * @param updatedItems nuevos ítems de la venta
     * @return la venta actualizada
     */
    Sale updateSale(Long saleId, Long userId, List<SaleItem> updatedItems);

    /**
     * Busca ventas según un criterio de búsqueda.
     *
     * @param query texto de búsqueda
     * @return lista de ventas que coinciden con el criterio
     */
    List<Sale> searchSales(String query);
}
