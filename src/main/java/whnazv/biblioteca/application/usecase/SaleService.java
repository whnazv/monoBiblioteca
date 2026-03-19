package whnazv.biblioteca.application.usecase;

import lombok.RequiredArgsConstructor;
import whnazv.biblioteca.application.port.in.SaleUseCase;
import whnazv.biblioteca.application.port.out.BookRepositoryPort;
import whnazv.biblioteca.application.port.out.SaleRepositoryPort;
import whnazv.biblioteca.domain.exception.InsufficientStockException;
import whnazv.biblioteca.domain.exception.NotFoundException;
import whnazv.biblioteca.domain.model.Book;
import whnazv.biblioteca.domain.model.Sale;
import whnazv.biblioteca.domain.model.SaleItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que implementa la lógica de negocio relacionada con ventas.
 * Gestiona validaciones, actualización de stock y delega en el repositorio.
 */
@Service
@RequiredArgsConstructor
public class SaleService implements SaleUseCase {

    private final SaleRepositoryPort saleRepositoryPort;
    private final BookRepositoryPort bookRepositoryPort;

    /**
     * Crea una nueva venta.
     *
     * @param userId ID del usuario que realiza la venta
     * @param items lista de ítems de la venta
     * @return venta creada
     */
    @Override
    public Sale createSale(Long userId, List<SaleItem> items) {

        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("La venta debe contener al menos un libro.");
        }

        List<SaleItem> finalItems = new ArrayList<>();

        for (SaleItem item : items) {

            Book book = bookRepositoryPort.findById(item.getBookId())
                    .orElseThrow(() -> new NotFoundException("Libro no encontrado"));

            if (book.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuficiente para el libro: " + book.getTitle());
            }

            book.decreaseStock(item.getQuantity());
            bookRepositoryPort.save(book);

            SaleItem finalItem = new SaleItem(
                    item.getBookId(),
                    item.getQuantity(),
                    book.getPrice()
            );

            finalItems.add(finalItem);
        }

        Sale sale = new Sale(null, userId, finalItems);

        return saleRepositoryPort.save(sale);
    }

    /**
     * Obtiene todas las ventas.
     */
    @Override
    public List<Sale> getAllSales() {
        return saleRepositoryPort.findAll();
    }

    /**
     * Obtiene las ventas de un usuario.
     */
    @Override
    public List<Sale> getSalesByUserId(Long userId) {
        return saleRepositoryPort.findByUserId(userId);
    }

    /**
     * Obtiene una venta por ID.
     */
    @Override
    public Sale getSaleById(Long saleId) {
        return saleRepositoryPort.findById(saleId)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada con ID: " + saleId));
    }

    /**
     * Elimina una venta por ID.
     */
    @Override
    public boolean deleteSale(Long saleId) {
        if (!saleRepositoryPort.existsById(saleId)) {
            return false;
        }
        saleRepositoryPort.deleteById(saleId);
        return true;
    }

    /**
     * Actualiza una venta existente.
     *
     * @param saleId ID de la venta
     * @param userId ID del usuario
     * @param updatedItems nuevos ítems
     * @return venta actualizada
     */
    @Override
    public Sale updateSale(Long saleId, Long userId, List<SaleItem> updatedItems) {

        Sale sale = saleRepositoryPort.findById(saleId)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada"));

        for (SaleItem oldItem : sale.getItems()) {

            Book oldBook = bookRepositoryPort.findById(oldItem.getBookId())
                    .orElseThrow(() -> new NotFoundException("Libro no encontrado"));

            oldBook.increaseStock(oldItem.getQuantity());
            bookRepositoryPort.save(oldBook);
        }

        List<SaleItem> finalItems = new ArrayList<>();

        for (SaleItem item : updatedItems) {

            Book book = bookRepositoryPort.findById(item.getBookId())
                    .orElseThrow(() -> new NotFoundException("Libro no encontrado"));

            if (book.getStock() < item.getQuantity()) {
                throw new InsufficientStockException("Stock insuficiente para el libro: " + book.getTitle());
            }

            book.decreaseStock(item.getQuantity());
            bookRepositoryPort.save(book);

            SaleItem finalItem = new SaleItem(
                    item.getBookId(),
                    item.getQuantity(),
                    book.getPrice()
            );

            finalItems.add(finalItem);
        }

        Sale updatedSale = new Sale(saleId, userId, finalItems);

        return saleRepositoryPort.save(updatedSale);
    }

    /**
     * Busca ventas según un criterio.
     */
    @Override
    public List<Sale> searchSales(String query) {
        return saleRepositoryPort.searchSales(query);
    }
}
