package com.assignemnt.demo.service;

import com.assignemnt.demo.model.Cart;
import com.assignemnt.demo.model.Order;
import com.assignemnt.demo.model.OrderDetail;
import com.assignemnt.demo.repository.CartRepository;
import com.assignemnt.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    public OrderServiceImpl(CartRepository cartRepository, OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(final Integer customerId) {
        Optional<Cart> cartOptional = cartRepository.findCartByCustomer_Id(customerId);
        String message = "No cart item exist with customer id: " + customerId;
        Cart cart = cartOptional.orElseThrow(() -> new IllegalArgumentException(message));
        if (cart.getCartItems().size() == 0) throw new IllegalArgumentException(message);
        Order order = new Order(cart.getCustomer(), new ArrayList<>(), cart.getTotalCost(), "READY_TO_DELIVER");
        List<OrderDetail> orderDetails = cart.getCartItems().stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail(order, item.getProduct(), item.getQuantity());
            return orderDetail;
        }).collect(Collectors.toList());
        order.setOrderDetail(orderDetails);

        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
