package ru.kildeev.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.models.Cart;
import ru.kildeev.market.entities.Product;
import ru.kildeev.market.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;

    private final ProductService productService;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Продукт с таким id не найден, id" + productId));
        tempCart.add(product);
    }

    public void clear() {
        tempCart.clear();
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }
}
