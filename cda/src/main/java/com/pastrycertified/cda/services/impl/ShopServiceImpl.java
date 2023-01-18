package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.models.Shop;
import com.pastrycertified.cda.repository.ShopRepository;
import com.pastrycertified.cda.services.ShopService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ObjectsValidator<ShopDto> validator;

    @Override
    public Integer save(ShopDto dto) {
        validator.validate(dto);
        Shop shop = ShopDto.toEntity(dto);
        return shopRepository.save(shop).getId();
    }

    @Override
    public List<ShopDto> findAll() {
        return null;
    }

    @Override
    public ShopDto findById(Integer id) {
        return null;
    }

    @Override
    public Integer updateByid(Integer id, ShopDto dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
