package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.models.Shop;
import com.pastrycertified.cda.repository.ShopRepository;
import com.pastrycertified.cda.services.ShopService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ObjectsValidator<ShopDto> validator;

    @Override
    public Shop save(String name, String description, MultipartFile image) {
       Shop shop = shopRepository.findByName(name)
               .orElse(null);
        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        if (shop == null) {
            return shopRepository.save(
                    Shop.builder()
                            .name(name)
                            .description(description)
                            .image(Base64.getEncoder().encodeToString(fileName.getBytes()))
                            .build()
            );
        }
        return shop;
    }

    @Override
    public Integer save(ShopDto dto) {
        return null;
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
