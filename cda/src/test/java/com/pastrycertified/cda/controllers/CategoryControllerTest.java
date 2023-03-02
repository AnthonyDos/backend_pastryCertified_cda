package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.CategoryDto;
import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.repository.CategoryRepository;
import com.pastrycertified.cda.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    CategoryServiceImpl categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        this.categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    @DisplayName("should return all categories")
    void findAll() {

        CategoryDto mockCategoryDto = new CategoryDto(75, "cake");
        CategoryDto mockCategoryDto1 = new CategoryDto(72, "tarte");
        List<CategoryDto>categoryList = new ArrayList<>();
        categoryList.add(mockCategoryDto);
        categoryList.add(mockCategoryDto1);

        doReturn(Arrays.asList(mockCategoryDto,mockCategoryDto1)).when(categoryRepository).findAll();

        List<CategoryDto>categoryDtoResponse = categoryService.findAll();
        Assertions.assertEquals(2,categoryDtoResponse.size(), "findAll should return 2 categories");

    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}