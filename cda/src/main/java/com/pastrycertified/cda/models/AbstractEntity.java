package com.pastrycertified.cda.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;

//    private String lastname;
//
//    private String firstname;
//
//    @Column(unique = true)
//    private String email;
//
//    private String password;
//
//    private String phone;

}
