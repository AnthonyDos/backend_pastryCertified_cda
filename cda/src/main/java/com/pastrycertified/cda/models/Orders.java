package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class)
})
public class Orders extends AbstractEntity {

    private UUID order_number;

    @NotNull(message = "La liste de produit ne doit pas être null")
    @NotEmpty(message = "la liste de produit ne doit pas être vide")
    @Type( type = "json" )
    @Column(columnDefinition = "json")
    private List list_product;

    private LocalDate order_date;

    @NotNull(message = "La date de livraison ne doit pas être null")
    @NotEmpty(message = "la liste de livraison ne doit pas être vide")
    private String order_delivered;

    @NotNull(message = "Le prix ne doit pas être null")
    @NotEmpty(message = "le prix de produit ne doit pas être vide")
    private String price;

    private String order_status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
