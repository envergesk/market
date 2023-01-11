package ru.kildeev.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.entities.Order;
import ru.kildeev.market.entities.User;
import ru.kildeev.market.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CartService cartService;
    private final OrderRepository orderRepository;

    public void createOrder(User user) {
        Order order = new Order();
    }
}
