package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.OrderDto;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final UsersService usersService;

    public List<Order> findAllByUserId(Long userId) {
        User user = usersService.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Нет юзеров с таким id: " + userId));
        return ordersRepository.findAllByUserId(user.getId());
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public void deleteById(Long id) {
        ordersRepository.deleteById(id);
    }

    public Order save(Order order) {
        return ordersRepository.save(order);
    }

    @Transactional
    public Order update(OrderDto orderDto) {
        Order order = ordersRepository.findById(orderDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Заказа с таким id не существует: " + orderDto.getId()));
        order.setUserId(orderDto.getUserId());
        order.setAddress(orderDto.getAddress());
        order.setOrderItems(orderDto.getOrderItems());
        order.setPhone(orderDto.getPhone());
        order.setTotalPrice(orderDto.getTotalPrice());
        return order;
    }
}
