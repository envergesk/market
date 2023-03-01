package ru.kildeev.market.core.services;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import ru.kildeev.market.api.CartDto;
import ru.kildeev.market.core.entities.Order;
import ru.kildeev.market.core.entities.OrderItem;
import ru.kildeev.market.core.integrations.CartServiceIntegration;
import ru.kildeev.market.core.repositories.OrderRepository;
import ru.kildeev.market.core.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setOrderItems(cartDto.getItems().stream().map(
                cartItemDto -> new OrderItem(
                        productRepository.findById(cartItemDto.getProductId()).get(),
                        order,
                        cartItemDto.getQuantity(),
                        cartItemDto.getPricePerProduct(),
                        cartItemDto.getPrice()
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clear();
    }
}
