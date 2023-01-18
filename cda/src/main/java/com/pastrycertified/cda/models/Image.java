package com.pastrycertified.cda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image extends AbstractEntity{

    private byte[] image;

    @OneToOne
    //@JoinColumn(name = "id", referencedColumnName = "id")
    private Shop shop;
}
