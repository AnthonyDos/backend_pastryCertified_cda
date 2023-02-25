package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Invoice;
import com.pastrycertified.cda.models.Orders;
import com.pastrycertified.cda.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class InvoiceDto {

    private Integer id;

    private String invoice_number;

    private String details;

    private Integer orderId;

    private Integer userId;

    public static InvoiceDto fromEntity(Invoice invoice) {

        return InvoiceDto.builder()
                .id(invoice.getId())
                .invoice_number(invoice.getInvoice_number())
                .details(invoice.getDetails())
                .orderId(invoice.getOrders().getId())
                .userId(invoice.getUser().getId())
                .build();
    }

    public static Invoice toEntity(InvoiceDto invoice) {
        return Invoice.builder()
                .id(invoice.getId())
                .invoice_number(invoice.getInvoice_number())
                .details(invoice.getDetails())
                .orders(
                        Orders.builder()
                                .id(invoice.getOrderId())
                                .build()
                )
                .user(
                        User.builder()
                                .id(invoice.getUserId())
                                .build()
                )
                .build();
    }
}
