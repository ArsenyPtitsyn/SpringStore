package com.geekbrains.spring.web.validators;

import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryValidator {
    public void validate(CategoryDto categoryDto) {
        List<String> errors = new ArrayList<>();
        if (categoryDto.getTitle().isBlank()) {
            errors.add("Категория не может иметь пустое название");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }
}
