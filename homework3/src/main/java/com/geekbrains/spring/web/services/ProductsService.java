package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.converters.ProductConverter;
import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.repositories.ProductsRepository;
import com.geekbrains.spring.web.repositories.specifications.ProductsSpecifications;
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
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductConverter productConverter;

    public Page<Product> findAll(Integer minPrice, Integer maxPrice, String partTitle, String categoryTitle, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductsSpecifications.titleLike(partTitle));
        }
        if (categoryTitle != null) {
            spec = spec.and(ProductsSpecifications.categoryTitleIs(categoryTitle));
        }

        return productsRepository.findAll(spec, PageRequest.of(page - 1, 8));
    }

    public Optional<Product> findById(Long id) {
        return productsRepository.findById(id);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productsRepository.save(product);
    }

    public List<Category> findCategoriesOfProduct(Product product) {
        return product.getCategories();
    }

    @Transactional
    public Product update(ProductDto productDto) {

        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("???????????????????? ???????????????? ????????????????, ???? ???????????? ?? ????????, id: " + productDto.getId()));
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        List<Category> categories = productDto.getCategories().stream()
                .map(c -> {
                    Category category = new Category();
                    category.setId(c.getId());
                    category.setTitle(c.getTitle());
                    category.setProducts(c.getProducts().stream().map(productConverter::dtoToEntity).collect(Collectors.toList()));
                    return category;
                }).collect(Collectors.toList());
        return product;
    }
}
