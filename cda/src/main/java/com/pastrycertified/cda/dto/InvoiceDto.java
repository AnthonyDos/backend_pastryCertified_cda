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

    private OrderDto orderDto;

    private UserDto userDto;

    private Integer userId;

    public static InvoiceDto fromEntity(Invoice invoice) {

        return InvoiceDto.builder()
                .id(invoice.getId())
                .invoice_number(invoice.getInvoice_number())
                .details(invoice.getDetails())
                .orderId(invoice.getOrders().getId())
                .orderDto(OrderDto.fromEntity(invoice.getOrders()))
                .userId(invoice.getUser().getId())
                .userDto(UserDto.fromEntity(invoice.getUser()))
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
