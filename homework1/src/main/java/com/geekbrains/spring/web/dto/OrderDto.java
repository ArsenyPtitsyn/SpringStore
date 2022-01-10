package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.entities.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderItem> orderItems;
    private int totalPrice;
    private String address;
    private String phone;
}
