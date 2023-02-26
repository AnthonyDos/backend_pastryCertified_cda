package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.models.Options;
import com.pastrycertified.cda.models.Products;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
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

    private String categoryName;

    @Nullable
    private String optionsName;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    @Nullable
    private String cream;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    @Nullable
    private String finition;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    @Nullable
    private String paste;

    public static ProductsDto fromEntity(Products products) {

        return ProductsDto.builder()
                .id(products.getId())
                .name(products.getName())
                .ingredients(products.getIngredients())
                .price(products.getPrice())
                .image(products.getImage())
                .categoryName(products.getCategory().getName())
                .optionsName(products.getOptions().getTypeOption())
                .cream(products.getOptions().getCream())
                .finition(products.getOptions().getFinition())
                .paste(products.getOptions().getPaste())
                .build();
    }

    public static Products toEntity(ProductsDto products) {

        return Products.builder()
                .id(products.getId())
                .name(products.getName())
                .ingredients(products.getIngredients())
                .price(products.getPrice())
                .image(products.getImage())
                .category(
                        Category.builder()
                                .name(products.getName())
                                .build()
                )
                .options(
                        Options.builder()
                                .typeOption(products.getOptionsName())
                                .cream(products.getCream())
                                .finition(products.getFinition())
                                .paste(products.getPaste())
                                .build()
                )
                .build();
    }
}
