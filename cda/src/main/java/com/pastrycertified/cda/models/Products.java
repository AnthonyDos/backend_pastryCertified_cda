package com.pastrycertified.cda.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pastrycertified.cda.models.Abstract.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;
import java.util.Map;
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

    @OneToOne
    private Options options;

//    @ManyToMany
//    @JoinTable(
//            name = "optioncream_product",
//            joinColumns = @JoinColumn(name = "id_product"),
//            inverseJoinColumns = @JoinColumn(name = "id_optioncream")
//    )
//    Set<OptionsCream> otpionsCreamProduct;



}
