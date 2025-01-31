package com.andersonfariasdev.easycatalogbackend.dto;

import lombok.Data;

@Data
public class ProductCreateDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long category;
    private boolean available;
}
