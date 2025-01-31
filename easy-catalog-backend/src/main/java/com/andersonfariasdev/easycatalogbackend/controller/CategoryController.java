package com.andersonfariasdev.easycatalogbackend.controller;

import com.andersonfariasdev.easycatalogbackend.dto.CategoryDto;
import com.andersonfariasdev.easycatalogbackend.dto.ProductCreateDto;
import com.andersonfariasdev.easycatalogbackend.dto.ProductDto;
import com.andersonfariasdev.easycatalogbackend.service.CategoryService;
import com.andersonfariasdev.easycatalogbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

}
