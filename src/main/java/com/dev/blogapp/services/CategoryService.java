package com.dev.blogapp.services;

import com.dev.blogapp.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(Integer id);

    List<CategoryDto> getAllCategory();

    CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);

    void deleteCategory(Integer id);

}
