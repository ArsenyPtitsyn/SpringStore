package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);
}
