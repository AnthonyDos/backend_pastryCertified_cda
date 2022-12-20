package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.AddressDto;

public interface AddressService extends AbstractService<AddressDto> {

    AddressDto findAddressByIdUser(Integer id);
}
