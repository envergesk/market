package ru.kildeev.market.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kildeev.market.api.CartDto;
import ru.kildeev.market.api.StringResponse;
import ru.kildeev.market.carts.converters.CartConverter;
import ru.kildeev.market.carts.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{uuid}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.add(targetUuid, id);
    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String uuid) {
        String targetUuid = getCartUuid(username, uuid);
        return cartConverter.entityToDto(cartService.getCurrentCart(targetUuid));
    }

    @GetMapping("/{uuid}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username,@PathVariable String uuid){
        String targetUuid = getCartUuid(username, uuid);
        cartService.clear(targetUuid);
    }

    @GetMapping("/{uuid}/remove/{id}")
    public void deleteFromCart(@RequestHeader(name = "username", required = false) String username,@PathVariable String uuid, @PathVariable Long id) {
        String targetUuid = getCartUuid(username, uuid);
        cartService.remove(targetUuid, id);
    }

    private String getCartUuid(String username, String uuid) {
        if (username != null) {
            return username;
        }
        return uuid;
    }
}
