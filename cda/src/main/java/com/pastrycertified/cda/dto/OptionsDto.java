package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Options;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;

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
    private String cream;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private String finition;

    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private String paste;

    private String nameCategory;

    public static OptionsDto fromEntity(Options options) {

        return OptionsDto.builder()
                .id(options.getId())
                .typeOption(options.getTypeOption())
                .cream(options.getCream())
                .finition(options.getFinition())
                .paste(options.getPaste())
                .build();
    }

    public static Options toEntity(OptionsDto options) {

        return Options.builder()
                .id(options.getId())
                .typeOption(options.getTypeOption())
                .cream(options.getCream())
                .finition(options.getFinition())
                .paste(options.getPaste())
                .build();
    }
}
