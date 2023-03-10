package com.pastrycertified.cda.controllers;


import com.pastrycertified.cda.dto.CategoryDto;
import com.pastrycertified.cda.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${url.categorie}")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("${all.categories}")
    public ResponseEntity<List<CategoryDto>>findAll() { return ResponseEntity.ok(categoryService.findAll());}

    @GetMapping("${category.id}")
    public ResponseEntity<CategoryDto> findById(
            @PathVariable("category-id") Integer categoryId
    ) {
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("${delete.category.id}")
    public ResponseEntity<Void>delete(
            @PathVariable("category-id") Integer categoryId
    ) {
        categoryService.delete(categoryId);
        return ResponseEntity.accepted().build();
    }
}
