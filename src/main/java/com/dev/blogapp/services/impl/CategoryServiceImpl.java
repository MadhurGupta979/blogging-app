package com.dev.blogapp.services.impl;

import com.dev.blogapp.entities.Category;
import com.dev.blogapp.exceptions.ResourceNotFoundException;
import com.dev.blogapp.payloads.CategoryDto;
import com.dev.blogapp.repository.CategoryRepo;
import com.dev.blogapp.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepo.save(category);
        CategoryDto savedCateogryDto = this.modelMapper.map(savedCategory, CategoryDto.class);
        return savedCateogryDto;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.stream().forEach(c -> categoryDtos.add(this.modelMapper.map(c, CategoryDto.class)));
        return categoryDtos;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
        Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", catId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category savedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer id) {
        Category category = this.categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        this.categoryRepo.delete(category);
    }
}
