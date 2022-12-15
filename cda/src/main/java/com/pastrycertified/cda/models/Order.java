package com.pastrycertified.cda.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order extends AbstractEntity{

    private String order_number;

    private String list_product;

    private LocalDate order_date;

    private LocalDate order_delivered;

    private String price;

    private String order_status;

}
