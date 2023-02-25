package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> findAll();
    
    void delete(Integer id);

    CategoryDto findById(Integer categoryId);
}
