package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.ProductsDto;
import com.pastrycertified.cda.models.Products;
import com.pastrycertified.cda.repository.ProductsRepository;
import com.pastrycertified.cda.services.ProductsService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;

    @Override
    public Products save(String name, String ingredients, String price, MultipartFile image) {

        Products products = productsRepository.findByName(name)
                .orElse(null);

        String fileName = StringUtils.cleanPath(image.getOriginalFilename());
        if (products == null) {
            return productsRepository.save(
                    Products.builder()
                            .name(name)
                            .ingredients(ingredients)
                            .price(price)
                            .image(Base64.getEncoder().encodeToString(fileName.getBytes()))
                            .build()
            );
        }
        return products;
    }

    @Override
    public Integer save(ProductsDto dto) {
        return null;
    }

    @Override
    public List<ProductsDto> findAll() {
        return productsRepository.findAll()
                .stream()
                .map(ProductsDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ProductsDto findById(Integer id) {
        return productsRepository.findById(id)
                .map(ProductsDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun produit correspondant à cette id :" + id));
    }

    @Override
    public Integer updateByid(Integer id, ProductsDto dto) {
        Products products = productsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aucun produit ne correspond à l' id" + id));

        if (dto.getName() != null) {
            products.setName(dto.getName());
        }
        if (dto.getIngredients() != null) {
            products.setIngredients(dto.getIngredients());
        }
        if (dto.getPrice() != null) {
            products.setPrice(dto.getPrice());
        }
        productsRepository.save(products);
        return products.getId();
    }

    @Override
    public void delete(Integer id) {
        productsRepository.deleteById(id);
    }

}
