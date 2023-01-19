package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop extends AbstractEntity {

    private String name;

    private String description;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @Null
    private String image;

}
