package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.ProductDto;
import com.geekbrains.spring.web.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryConverter categoryConverter;

    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setTitle(productDto.getTitle());
        product.setCategories(productDto.getCategories().stream().map(categoryConverter::dtoToEntity).collect(Collectors.toList()));
        return product;
    }

    public ProductDto entityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setTitle(product.getTitle());
        productDto.setCategories(product.getCategories().stream().map(categoryConverter::entityToDto).collect(Collectors.toList()));
        return productDto;
    }
}
