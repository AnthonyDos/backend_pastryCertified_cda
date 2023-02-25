package com.pastrycertified.cda.models;


import com.pastrycertified.cda.models.Abstract.AbstractEntity;
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
public class Invoice extends AbstractEntity {

    private String invoice_number;

    private String details;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
