package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Image;
import com.pastrycertified.cda.models.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ShopDto {

    private Integer id;

    @NotNull(message = "Le nom ne doit pas être null")
    @NotEmpty(message = "le nom ne doit pas être vide")
    private String name;

    @NotNull(message = "La description ne doit pas être null")
    @NotEmpty(message = "La description ne doit pas être vide")
    private String description;

    private String image;



    public static ShopDto fromEntity(Shop shop) {

        return ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .image(shop.getImage())
                .build();
    }

    public static Shop toEntity(ShopDto shop) {

        return Shop.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .image(shop.getImage())
                .build();
    }
}
