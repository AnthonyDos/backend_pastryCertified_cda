package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String civility;

    @NotNull(message = "Le nom ne doit pas être null")
    @NotEmpty(message = "le nom ne doit pas être vide")
    private String lastname;

    @NotNull(message = "Le prénom ne doit pas être null")
    @NotEmpty(message = "le prénom ne doit pas être vide")
    private String firstname;

    @NotNull(message = "La date anniversaire ne doit pas être null")
    @NotEmpty(message = "La date anniversaire ne doit pas être vide")
    private String birth_day;

    @NotNull(message = "L'email ne doit pas être null")
    @NotEmpty(message = "L'email ne doit pas être vide")
    @NotBlank(message = "L'email ne doit pas contenir d'espace")
    @Email(message = "L'email n'est pas conforme")
    private String email;

    @NotNull(message = "Le mot de passe ne doit pas être null")
    @NotEmpty(message = "Le mot de passe ne doit pas être vide")
    @NotBlank(message = "le mot de passe ne doit pas contenir d'espace")
    @Size(min = 6, max = 15, message = "Le mot de passe doit être compris en 6 et 15 caractères")
    private String password;

    @NotNull(message = "Le téléphone ne doit pas être null")
    @NotEmpty(message = "Le téléphone ne doit pas être vide")
    private String phone;

    private String role_name;

    private AddressDto address;

    private Integer address_id;

    public static UserDto fromEntity(User user) {

        return UserDto.builder()
                .id(user.getId())
                .civility(user.getCivility())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .birth_day(user.getBirth_day())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .role_name(user.getRole().getName())
                .address(AddressDto.fromEntity(user.getAddress()))
                .address_id(user.getAddress().getId())
                .build();
    }

    public static User toEntity(UserDto user) {

        return User.builder()
                .id(user.getId())
                .civility(user.getCivility())
                .lastname(user.getLastname())
                .firstname(user.getFirstname())
                .birth_day(user.getBirth_day())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .role(
                        Role.builder()
                                .name(user.role_name)
                                .build()
                )
                .address(
                        Address.builder()
                                .address_number(user.getAddress().getAddress_number())
                                .street(user.getAddress().getStreet())
                                .zipCode(user.getAddress().getZipCode())
                                .city(user.getAddress().getCity())
                                .country(user.getAddress().getCountry())
                                .build()
                )
                .build();
    }
}
