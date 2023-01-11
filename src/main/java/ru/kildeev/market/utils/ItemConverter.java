package ru.kildeev.market.utils;

import ru.kildeev.market.entities.Order;
import ru.kildeev.market.entities.OrderItem;
import ru.kildeev.market.entities.Product;
import ru.kildeev.market.models.CartItem;

import javax.persistence.*;

public class ItemConverter {

//   public static OrderItem cartItemToOrderItem(CartItem cartItem) {
//       return new OrderItem(null, cartItem.)
//   }

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "price")
    private int price;

}
