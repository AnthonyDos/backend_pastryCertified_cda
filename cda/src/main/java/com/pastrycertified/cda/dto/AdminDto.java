package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Admin;
import com.pastrycertified.cda.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AdminDto {

    private Integer id;

    @NotNull(message = "Le nom ne doit pas être null")
    @NotEmpty(message = "le nom ne doit pas être vide")
    private String lastname;

    @NotNull(message = "Le prénom ne doit pas être null")
    @NotEmpty(message = "le prénom ne doit pas être vide")
    private String firstname;

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

    private String cast_member;

    private String role_name;

    public static AdminDto fromEntity(Admin admin) {

        return AdminDto.builder()
                .id(admin.getId())
                .lastname(admin.getLastname())
                .firstname(admin.getFirstname())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .phone(admin.getPhone())
                .cast_member(admin.getCast_member())
                .role_name(admin.getRole().getName())
                .build();
    }

    public static Admin toEntity(AdminDto admin) {
        return Admin.builder()
                .id(admin.getId())
                .lastname(admin.getLastname())
                .firstname(admin.getFirstname())
                .email(admin.getEmail())
                .password(admin.getPassword())
                .phone(admin.getPhone())
                .cast_member(admin.getCast_member())
                .role(
                        Role.builder()
                                .name(admin.role_name)
                                .build()
                )
                .build();
    }
}
