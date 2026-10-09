package com.app.ecom.service;

import com.app.ecom.Model.OrderStatus;
import com.app.ecom.dao.OrderRepository;
import com.app.ecom.dao.UserRepository;
import com.app.ecom.dto.OrderItemDTO;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.entity.CartItem;
import com.app.ecom.entity.OrderItem;
import com.app.ecom.entity.Orders;
import com.app.ecom.entity.User;
import jakarta.persistence.criteria.Order;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceIMPL implements  OrderService{


    private final CartService cartService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    public Optional<OrderResponse> createOrder(long userId) {

        //validate Cart item of User
        List<CartItem> cartItems = cartService.fetchCartItems(userId);

        if(cartItems.isEmpty()){
            return  Optional.empty();
        }

        Optional<User> user= userRepository.findById(userId);

        if(user.isEmpty()){
            return  Optional.empty();
        }

        BigDecimal totalPrice = cartItems.stream().map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        Orders order =new Orders();
        order.setUserId(String.valueOf(userId));
        order.setTotalAmount(totalPrice);
        order.setStatus(OrderStatus.CONFIRMED);

        List<OrderItem> orderItem = cartItems.stream()
                .map(cart -> new OrderItem(null, String.valueOf(cart.getProduct().getId())
                        , cart.getQuantity(), cart.getPrice(), order)).toList();

        order.setItems(orderItem);
        Orders savedOrder=orderRepository.save(order);

        // Clear the cart
        cartService.clearCart(userId);
         return Optional.of(mapToOrderResponse(savedOrder));
    }


    private List<OrderItemDTO> mapToOrderItemDTOs(List<OrderItem> items) {
        return items.stream()
                .map(item -> new OrderItemDTO(
                        item.getId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice(),
                        item.getPrice().multiply(new BigDecimal(item.getQuantity()))
                )).collect(Collectors.toList());
    }

    private OrderResponse mapToOrderResponse(Orders order) {
        return new OrderResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream()
                        .map(orderItem -> new OrderItemDTO(
                                orderItem.getId(),
                                orderItem.getProductId(),
                                orderItem.getQuantity(),
                                orderItem.getPrice(),
                                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
                        ))
                        .toList(),
                order.getCreatedAt()
        );
    }
}
