package com.dev.blogapp.controller;

import com.dev.blogapp.payloads.ApiResponse;
import com.dev.blogapp.payloads.CategoryDto;
import com.dev.blogapp.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto categoryDto1 = this.categoryService.saveCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer id) {
        return new ResponseEntity<>(this.categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        return new ResponseEntity<>(this.categoryService.getAllCategory(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category with id : " + id + " deleted.", true), HttpStatus.OK);
    }
}
