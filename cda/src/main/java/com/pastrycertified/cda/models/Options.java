package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class Options extends AbstractEntity {

    @Nullable
    private String typeOption;

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

}
