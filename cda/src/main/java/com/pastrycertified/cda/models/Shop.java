package com.pastrycertified.cda.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.IOException;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop extends AbstractEntity {

    private String name_shop;

    private String description;

    private byte[] image;


//    @OneToOne
//    @JoinColumn(name = "id")
//    private Image image;
}
