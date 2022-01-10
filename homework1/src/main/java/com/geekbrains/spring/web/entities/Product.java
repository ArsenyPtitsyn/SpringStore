package com.geekbrains.spring.web.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "productId")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    List<OrderItem> orderItems;

    public Product(Long id, String title, Integer price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
