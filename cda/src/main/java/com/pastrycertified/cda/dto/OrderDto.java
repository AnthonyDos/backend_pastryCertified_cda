package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Orders;
import com.pastrycertified.cda.models.User;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class OrderDto {

    private Integer id;

    private UUID order_number;

    @NotNull(message = "La liste produit ne doit pas être null")
    @NotEmpty(message = "La liste produit ne doit pas être vide")
    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private List list_product;

    private LocalDate order_date;

    @NotNull(message = "La date livraison ne doit pas être null")
    @NotEmpty(message = "La date livraison ne doit pas être vide")
    private String order_delivered;

    @NotNull(message = "Le prix ne doit pas être null")
    @NotEmpty(message = "Le prix ne doit pas être vide")
    private String price;

    private String order_status;

    private Integer user_id;

    private UserDto userDto;


    public static OrderDto fromEntity(Orders order) {
        return OrderDto.builder()
                .id(order.getId())
                .order_number(order.getOrder_number())
                .list_product(order.getList_product())
                .order_date(order.getOrder_date())
                .order_delivered(order.getOrder_delivered())
                .price(order.getPrice())
                .order_status(order.getOrder_status())
                .user_id(order.getUser().getId())
                .userDto(UserDto.fromEntity(order.getUser()))
                .build();
    }

    public static Orders toEntity(OrderDto order) {
        return Orders.builder()
                .id(order.getId())
                .order_number(order.getOrder_number())
                .list_product(order.getList_product())
                .order_date(LocalDate.now())
                .order_delivered(order.getOrder_delivered())
                .price(order.getPrice())
                .order_status(order.getOrder_status())
                .user(
                        User.builder()
                                .id(order.getUser_id())
                                .build()
                )
                .build();
    }
}
