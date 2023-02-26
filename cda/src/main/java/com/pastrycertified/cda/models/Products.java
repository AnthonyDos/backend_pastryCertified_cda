package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products extends AbstractEntity {

    @Column(unique = true)
    private String name;

    private String ingredients;

    private String price;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToOne
    private Category category;

    @OneToOne
    @Nullable
    private Options options;

}
