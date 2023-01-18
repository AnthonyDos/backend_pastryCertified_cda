package com.pastrycertified.cda.controllers;


import com.pastrycertified.cda.dto.ProductsDto;
import com.pastrycertified.cda.models.Products;
import com.pastrycertified.cda.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService service;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/")
    public Products saveProduct(
            @RequestPart("name") String name,
            @RequestPart("ingredients") String ingredients,
            @RequestPart("price") String price,
            @RequestPart("file") MultipartFile image
        ) {
        return service.save(name, ingredients, price, image);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductsDto>findProductById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductsDto>>findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}
