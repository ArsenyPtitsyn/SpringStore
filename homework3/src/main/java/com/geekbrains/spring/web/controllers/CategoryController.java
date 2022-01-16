package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.CategoryConverter;
import com.geekbrains.spring.web.services.CategoriesService;
import com.geekbrains.spring.web.validators.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoriesService categoriesService;
    private final CategoryConverter categoryConverter;
    private final CategoryValidator categoryValidator;


}
