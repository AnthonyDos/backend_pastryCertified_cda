package com.pastrycertified.cda.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends AbstractEntity{

    private String civility;

    private String lastname;

    private String firstname;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;


    private String birth_day;

    @OneToOne
    private Address address;

    @OneToOne
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
//    }
//
//    @Override
//    public String getUsername() {
//        return getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }

}
