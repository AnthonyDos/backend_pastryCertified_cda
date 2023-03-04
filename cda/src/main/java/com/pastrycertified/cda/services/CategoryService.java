package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.CategoryDto;

import java.util.List;
import java.util.Optional;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface CategoryService {

    List<CategoryDto> findAll();
    
    void delete(Integer id);

    CategoryDto findById(Integer categoryId);
}
