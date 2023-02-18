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
@RequestMapping("${url.products}")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "${create.product}")
    public Products saveProduct(
            @RequestPart("name") String name,
            @RequestPart("ingredients") String ingredients,
            @RequestPart("price") String price,
            @RequestPart("file") MultipartFile image,
            @RequestPart("category") String categoryName,
            @RequestPart("typeOption") String typeOption,
            @RequestPart("cream") String cream,
            @RequestPart("finition") String finition,
            @RequestPart("paste") String paste
            ) {
        return service.save(name, ingredients, price, image, categoryName, typeOption, cream, finition, paste);
    }


    @GetMapping("${product.id}")
    public ResponseEntity<ProductsDto>findProductById(
            @PathVariable("product-id") Integer productId
    ) {
        return ResponseEntity.ok(service.findById(productId));
    }

    @GetMapping("${all.products}")
    public ResponseEntity<List<ProductsDto>>findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("${update.product}")
    public ResponseEntity<Integer> updateProduct(
            @PathVariable("product-id") Integer productId,
            @RequestBody ProductsDto products
    ) {
        return ResponseEntity.ok(service.updateByid(productId,products));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("${delete.product}")
    public ResponseEntity<Void>delete(
            @PathVariable("product-id") Integer productId
    ) {
        service.delete(productId);
        return ResponseEntity.accepted().build();
    }
}
