package com.pastrycertified.cda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderDto {

    private Integer id;
    private String order_number;
    private String list_product;
    private LocalDate order_date;
    private LocalDate order_delivered;
    private String price;
    private String order_status;
}
