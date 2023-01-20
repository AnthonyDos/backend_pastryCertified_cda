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

import javax.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

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
                            .image(fileName)
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
    public List<ShopDto>findAll() {
        return shopRepository.findAll()
                .stream()
                .map(ShopDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto findById(Integer id) {

        return shopRepository.findById(id)
                .map(ShopDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune boutique correspond à l'id "+ id));
    }

    @Override
    public Integer updateByid(Integer id, ShopDto dto) {
        Shop shop = shopRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucune boutique correspond à l'id "+ id));
        if (dto.getName() != null) {
            shop.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            shop.setDescription(dto.getDescription());
        }
        shopRepository.save(shop);
        return shop.getId();
    }

    @Override
    public void delete(Integer id) {
        shopRepository.deleteById(id);
    }
}
