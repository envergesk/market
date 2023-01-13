package ru.kildeev.market.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kildeev.market.core.entities.Order;
import ru.kildeev.market.core.entities.User;
import ru.kildeev.market.core.repositories.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void createOrder(User user) {
        Order order = new Order();
    }
}
