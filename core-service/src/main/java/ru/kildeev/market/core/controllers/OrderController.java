package ru.kildeev.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kildeev.market.api.OrderDto;
import ru.kildeev.market.core.converters.OrderConverter;
import ru.kildeev.market.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username /*,@RequestBody OrderData orderData*/) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
