package com.pastrycertified.cda.models;

import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Set;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products extends AbstractEntity {

    @Column(unique = true)
    private String name;

    private String ingredients;

    private String price;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @OneToOne
    private Category category;

//    @ManyToMany
//    @JoinTable(
//            name = "optioncream_product",
//            joinColumns = @JoinColumn(name = "id_product"),
//            inverseJoinColumns = @JoinColumn(name = "id_optioncream")
//    )
//    Set<OptionsCream> otpionsCreamProduct;

//    public Products(String name, String ingredients, String price, MultipartFile image) {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(String ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getImage() {
//        return image;
//    }

}
