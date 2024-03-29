package ru.kildeev.market.carts.models;

import lombok.Data;
import ru.kildeev.market.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data
public class Cart {
    private List<CartItem> items;

    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product) {
        for (CartItem item : items) {
            if (product.getId().equals(item.getProductId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void remove(Long productId) {
        if (items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }

    public void clear() {
        items.clear();
        totalPrice = BigDecimal.ZERO;
    }

    private void recalculate() {
        totalPrice = BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice = totalPrice.add(item.getPrice());

        }
    }
}
