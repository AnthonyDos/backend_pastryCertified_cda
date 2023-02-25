package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address extends AbstractEntity {

    private Integer address_number;

    private String street;

    private String zipCode;

    private String city;

    private String country;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user;

}
