package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.OrderItem;
import com.geekbrains.spring.web.repositories.OrderItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;

    public Optional<OrderItem> findById(Long id) {
        return orderItemsRepository.findById(id);
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }

    public void deleteById(Long id) {
        orderItemsRepository.deleteById(id);
    }

    public void changeQuantity(OrderItem orderItem, int delta) {
        if (orderItem.getQuantity() + delta <= 0)
            orderItemsRepository.deleteById(orderItem.getId());
        orderItem.setQuantity(orderItem.getQuantity() + delta);
    }
}
