package com.pastrycertified.cda.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products extends AbstractEntity{

    private String name;

    private String ingredients;

    private String price;

    private String photo;
}
