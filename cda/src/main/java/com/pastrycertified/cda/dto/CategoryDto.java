package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.models.Options;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryDto {

    private Integer id;

    private String name;

    private String typeOption;

    public static CategoryDto fromEntity(Category category) {

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toEntity(CategoryDto category) {

        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .options(
                        Options.builder()
                                .typeOption(category.getTypeOption())
                                .build()
                )
                .build();
    }
}
