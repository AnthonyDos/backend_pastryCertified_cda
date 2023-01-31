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

import javax.persistence.*;
import java.util.Map;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class Options extends AbstractEntity {

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

//    @Type( type = "json" )
//    @Column(columnDefinition = "json")
//    private Map<String, String> listOption;

//    @OneToOne
//    private Category category;

//    @ManyToMany
//    Set<Category> optionCreamCategory;

//    @ManyToMany
//    @JoinTable(
//            name = "optionCreamCategory",
//            joinColumns = @JoinColumn(name = "id_optionCream"),
//            inverseJoinColumns = @JoinColumn(name = "id_category")
//    )
//    Set<Category> optionCreamCategory;

//    @ManyToMany
//    Set<Products>optionCream;


}
