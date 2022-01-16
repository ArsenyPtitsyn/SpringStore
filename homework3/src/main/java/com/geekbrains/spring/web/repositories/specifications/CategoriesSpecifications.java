package com.geekbrains.spring.web.repositories.specifications;

import com.geekbrains.spring.web.entities.Category;
import org.springframework.data.jpa.domain.Specification;

public class CategoriesSpecifications {
    public static Specification<Category> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
}
