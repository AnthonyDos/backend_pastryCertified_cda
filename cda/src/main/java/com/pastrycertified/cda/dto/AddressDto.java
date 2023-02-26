package com.pastrycertified.cda.dto;

import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDto {

    private Integer id;

    @NotNull(message = "le numéro de rue ne peut pas être null")
    private Integer address_number;

    @NotNull(message = "le nom de la rue ne peut pas être null")
    @NotEmpty(message = "le nom de la rue ne peut pas être null")
    private String street;

    @NotNull(message = "le code postal ne peut pas être null")
    @NotEmpty(message = "le code postal ne peut pas être null")
    @Size(min = 5, max = 5, message = "le code postal le respect pas le format")
    private String zipCode;

    @NotNull(message = "le nom de la ville ne peut pas être null")
    @NotEmpty(message = "le nom de la ville ne peut pas être null")
    private String city;

    @NotNull(message = "le nom de du pays ne peut pas être null")
    @NotEmpty(message = "le nom de du pays ne peut pas être null")
    private String country;

    private Integer id_user;

    public static AddressDto fromEntity(Address address) {
        return AddressDto.builder()
                .id(address.getId())
                .address_number(address.getAddress_number())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .id_user(address.getUser().getId())
                .build();
    }

    public static Address toEntity(AddressDto address) {

        return Address.builder()
                .id(address.getId())
                .address_number(address.getAddress_number())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
//                .user(
//                        User.builder()
//                                .id(address.getId_user())
//                                .build()
//                )
                .build();
    }
}
