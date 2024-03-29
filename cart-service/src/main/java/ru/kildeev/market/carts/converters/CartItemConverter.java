package ru.kildeev.market.carts.converters;

import org.springframework.stereotype.Component;
import ru.kildeev.market.api.CartItemDto;
import ru.kildeev.market.carts.models.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        return cartItemDto;
    }
}
