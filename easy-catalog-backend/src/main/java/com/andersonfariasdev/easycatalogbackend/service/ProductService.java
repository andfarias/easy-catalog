package com.andersonfariasdev.easycatalogbackend.service;

import com.andersonfariasdev.easycatalogbackend.dto.ProductCreateDto;
import com.andersonfariasdev.easycatalogbackend.dto.ProductDto;
import com.andersonfariasdev.easycatalogbackend.entity.CategoryEntity;
import com.andersonfariasdev.easycatalogbackend.entity.ProductEntity;
import com.andersonfariasdev.easycatalogbackend.repository.CategoryRepository;
import com.andersonfariasdev.easycatalogbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ProductDto convertToDto(ProductEntity product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getName());
        productDto.setAvailable(product.isAvailable());
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.map(this::convertToDto);
    }

    public ProductDto createProduct(ProductCreateDto productDto) {
        ProductEntity product = new ProductEntity();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setAvailable(productDto.isAvailable());

        CategoryEntity category = categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        ProductEntity savedProduct = productRepository.save(product);
        return convertToDto(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Optional<ProductEntity> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            ProductEntity product = existingProduct.get();
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());
            product.setAvailable(productDto.isAvailable());

            CategoryEntity category = categoryRepository.findByName(productDto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);

            ProductEntity updatedProduct = productRepository.save(product);
            return convertToDto(updatedProduct);
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
