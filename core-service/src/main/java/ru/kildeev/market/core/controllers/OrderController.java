package ru.kildeev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kildeev.market.core.entities.User;
import ru.kildeev.market.core.services.OrderService;
import ru.kildeev.market.core.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(Principal principal /*,@RequestBody OrderData orderData*/) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException()); //TODO: доработать
        orderService.createOrder(user);
    }
}
