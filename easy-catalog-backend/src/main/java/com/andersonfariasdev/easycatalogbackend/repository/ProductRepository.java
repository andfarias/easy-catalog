package com.andersonfariasdev.easycatalogbackend.repository;

import com.andersonfariasdev.easycatalogbackend.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
