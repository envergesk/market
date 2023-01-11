package ru.kildeev.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kildeev.market.entities.User;
import ru.kildeev.market.services.OrderService;
import ru.kildeev.market.services.UserService;

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
