package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.CategoryDto;
import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.repository.CategoryRepository;
import com.pastrycertified.cda.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(CategoryDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("aucune catégorie correspondant à cet id " + categoryId));
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

}
