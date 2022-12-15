package com.pastrycertified.cda.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends AbstractEntity{

    private String lastname;

    private String firstname;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private BigDecimal cast_member;
}
