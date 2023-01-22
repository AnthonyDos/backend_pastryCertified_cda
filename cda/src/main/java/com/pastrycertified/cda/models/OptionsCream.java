package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OptionsCream extends AbstractEntity {

    private String cream_name;

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
