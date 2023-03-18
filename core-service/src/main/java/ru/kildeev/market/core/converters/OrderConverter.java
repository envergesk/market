package ru.kildeev.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kildeev.market.api.OrderDto;
import ru.kildeev.market.core.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto (Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUsername(order.getUsername());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        orderDto.setOrderItems(order.getOrderItems().stream()
                .map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}
