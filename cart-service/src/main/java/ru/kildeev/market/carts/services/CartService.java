package ru.kildeev.market.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.api.ProductDto;
import ru.kildeev.market.api.ResourceNotFoundException;
import ru.kildeev.market.carts.integrations.ProductServiceIntegration;
import ru.kildeev.market.carts.models.Cart;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;

    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        //Сделать запрос в бд
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void clear() {
        tempCart.clear();
    }

    public void remove(Long productId) {
        tempCart.remove(productId);
    }

    //TODO: isAvailable

}
