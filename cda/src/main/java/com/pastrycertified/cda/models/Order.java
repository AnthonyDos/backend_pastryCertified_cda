package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order extends AbstractEntity {

    @NotNull(message = "Le numéro de commande ne doit pas être null")
    @NotEmpty(message = "le numéro de commande ne doit pas être vide")
    private String order_number;

    @NotNull(message = "La liste de produit ne doit pas être null")
    @NotEmpty(message = "la liste de produit ne doit pas être vide")
    private String list_product;

    @NotNull(message = "La date de commande ne doit pas être null")
    @NotEmpty(message = "La date de commande ne doit pas être vide")
    private LocalDate order_date;

    @NotNull(message = "La date de livraison ne doit pas être null")
    @NotEmpty(message = "la liste de livraison ne doit pas être vide")
    private LocalDate order_delivered;

    @NotNull(message = "Le prix ne doit pas être null")
    @NotEmpty(message = "le prix de produit ne doit pas être vide")
    private String price;

    @NotNull(message = "Le statut de la commande ne doit pas être null")
    @NotEmpty(message = "le statut de la commande ne doit pas être vide")
    private String order_status;

}
