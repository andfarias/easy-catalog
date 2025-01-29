package com.andersonfariasdev.easycatalogbackend.repository;

import com.andersonfariasdev.easycatalogbackend.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
