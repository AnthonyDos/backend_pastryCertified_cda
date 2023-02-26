package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.AddressDto;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface AddressService extends AbstractService<AddressDto> {

    AddressDto findAddressByIdUser(Integer id);
}
