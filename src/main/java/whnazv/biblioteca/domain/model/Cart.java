package whnazv.biblioteca.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<CartItem> items = new ArrayList<>();

    
    public void addItem(Long bookId, String title, BigDecimal price, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem item = new CartItem();
        item.setBookId(bookId);
        item.setTitle(title);
        item.setPrice(price);
        item.setQuantity(quantity);
        items.add(item);
    }

    
    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

  
    public void clear() {
        items.clear();
    }

   
    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBookId().equals(bookId));
    }

   
    public void updateQuantity(Long bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

  
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
