package ru.geekbrains.spring.cloud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;

    public List<Product> findAll() {
        return productsRepository.findAll();
    }
}
