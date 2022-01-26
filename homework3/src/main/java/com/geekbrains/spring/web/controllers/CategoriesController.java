package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.CategoryConverter;
import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import com.geekbrains.spring.web.services.CategoriesService;
import com.geekbrains.spring.web.validators.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final CategoryConverter categoryConverter;
    private final CategoryValidator categoryValidator;

    @GetMapping
    public Page<CategoryDto> getAllCategories(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "title", required = false) String title
    ) {
        if (page < 1)
            page = 1;
        return categoriesService.findAll(title, page).map(categoryConverter::entityToDto);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {
        Category category = categoriesService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found, id: " + id));
        return categoryConverter.entityToDto(category);
    }

//    @PostMapping
//    public CategoryDto saveNewCategory(@RequestBody )
}
