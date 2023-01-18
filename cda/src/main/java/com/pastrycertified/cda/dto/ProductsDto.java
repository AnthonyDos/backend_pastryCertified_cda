package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductsDto {

    private Integer id;

    @NotNull(message = "Le nom ne doit pas être null")
    @NotEmpty(message = "le nom ne doit pas être vide")
    private String name;

    @NotNull(message = "Les ingrédients ne doivent pas être null")
    @NotEmpty(message = "Les ingrédients ne doivent pas être vide")
    private String ingredients;

    @NotNull(message = "Le prix ne doit pas être null")
    @NotEmpty(message = "Le prix ne doit pas être vide")
    private String price;

    private String image;

    public static ProductsDto fromEntity(Products products) {

        return ProductsDto.builder()
                .id(products.getId())
                .ingredients(products.getIngredients())
                .price(products.getPrice())
                .image(products.getImage())
                .build();
    }

    public static Products toEntity(ProductsDto products) {

        return Products.builder()
                .id(products.getId())
                .ingredients(products.getIngredients())
                .price(products.getPrice())
                .image(products.getImage())
                .build();
    }


}
