package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.repository.AddressRepository;
import com.pastrycertified.cda.services.AddressService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final ObjectsValidator<AddressDto> validator;


    @Override
    public Integer save(AddressDto dto) {
        System.out.println(dto);

        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);
        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return null;
    }

    @Override
    public AddressDto findById(Integer id) {
        return null;
    }

    @Override
    public AddressDto findAddressByIdUser(Integer id) {

        return addressRepository.findAddressByIdUser(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("L'utilisateur ne posséde pas d'adresse"));
    }

    @Override
    public Integer updateByid(Integer id, AddressDto dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
