package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.models.Options;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class OptionsDto {

    private Integer id;

    private String typeOption;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private Map<String, String> listOption;

    private String nameCategory;

    //private CategoryDto category;

    public static OptionsDto fromEntity(Options options) {

        return OptionsDto.builder()
                .id(options.getId())
                .typeOption(options.getTypeOption())
                .listOption(options.getListOption())
                //.nameCategory(options.getCategory().getName())
                .build();
    }

    public static Options toEntity(OptionsDto options) {

        return Options.builder()
                .id(options.getId())
                .typeOption(options.getTypeOption())
                .listOption(options.getListOption())
//                .category(
//                        Category.builder()
//                                .name(options.getNameCategory())
//                                .build()
//                )
                .build();
    }
}
