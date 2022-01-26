package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.CategoryConverter;
import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.CategoriesRepository;
import com.geekbrains.spring.web.repositories.specifications.CategoriesSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;
    public final CategoryConverter categoryConverter;

    public Page<Category> findAll(String title, Integer page) {
        Specification<Category> spec = Specification.where(null);
        if (title != null) {
            spec = spec.and(CategoriesSpecifications.titleLike(title));
        }

        return categoriesRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Optional<Category> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoriesRepository.deleteById(id);
    }

    public Category save(Category category) {
        return categoriesRepository.save(category);
    }

    @Transactional
    public Category update(CategoryDto categoryDto) {

        Category category = categoriesRepository.findById(categoryDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Невозможно обновить категорию. Она не надйена в базе, id: " + categoryDto.getId()));
        category.setTitle(categoryDto.getTitle());
        List<Product> products = categoryDto.getProducts().stream()
                .map(p -> {
                    Product product = new Product();
                    product.setId(p.getId());
                    product.setTitle(p.getTitle());
                    product.setPrice(p.getPrice());
                    product.setCategories(p.getCategories().stream().map(categoryConverter::dtoToEntity).collect(Collectors.toList()));
                    return product;
                }).collect(Collectors.toList());
        return category;
    }
}
