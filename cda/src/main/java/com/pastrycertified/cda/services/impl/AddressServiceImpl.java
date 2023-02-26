package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.models.User;
import com.pastrycertified.cda.repository.AddressRepository;
import com.pastrycertified.cda.repository.UserRepository;
import com.pastrycertified.cda.services.AddressService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;
    private final UserRepository userRepository;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        address.setUser(
                User.builder()
                        .id(dto.getId_user())
                        .build()
        );

        userRepository.updateByIdUser(addressRepository.save(address).getId(),dto.getId_user());
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("L'adresse n'existe pas"));
    }

    @Override
    public AddressDto findAddressByIdUser(Integer id) {
        return addressRepository.findAddressByIdUser(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("L'utilisateur ne posséde pas d'adresse"));
    }

    @Override
    public Integer updateByid(Integer id, AddressDto dto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("L'utilisateur ne posséde pas d'adresse"));
        if ( dto.getAddress_number() != null ) {
            address.setAddress_number(dto.getAddress_number());
        }

        if ( dto.getStreet() != null ) {
            address.setStreet(dto.getStreet());
        }

        if ( dto.getCity() != null ) {
            address.setCity(dto.getCity());
        }

        if ( dto.getZipCode() != null ) {
            address.setZipCode(dto.getZipCode());
        }

        if ( dto.getCountry() != null ) {
            address.setCountry(dto.getCountry());
        }

        addressRepository.save(address);
        return address.getId();
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);
    }

}
