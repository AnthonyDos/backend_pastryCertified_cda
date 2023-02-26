package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.ProductsDto;
import com.pastrycertified.cda.models.Category;
import com.pastrycertified.cda.models.Options;
import com.pastrycertified.cda.models.Products;
import com.pastrycertified.cda.repository.CategoryRepository;
import com.pastrycertified.cda.repository.OptionsRepository;
import com.pastrycertified.cda.repository.ProductsRepository;
import com.pastrycertified.cda.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * implémentation des services produits et qui implémente le service
 */
@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductsRepository productsRepository;
    private final CategoryRepository categoryRepository;
    private final OptionsRepository optionsRepository;

    @Override
    public Products save(String name, String ingredients, String price, MultipartFile image, String categoryName, String typeOption, String cream, String finition, String paste) {
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
                            .category(findOrCreateCategory(categoryName))
                            .options(findByTypeOption(typeOption,cream,finition,paste))
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
        if (dto.getCategoryName() != null) {
            products.setCategory(findOrCreateCategory(dto.getCategoryName()));
        }
        if (dto.getOptionsName() != null || dto.getCream() != null || dto.getFinition() != null || dto.getPaste() != null) {
            products.setOptions(findByTypeOption(dto.getOptionsName(), dto.getCream(), dto.getFinition(), dto.getPaste()));
        }
        productsRepository.save(products);
        return products.getId();
    }

    @Override
    public void delete(Integer id) {
        productsRepository.deleteById(id);
    }

    private Category findOrCreateCategory(String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElse(null);
        if (category == null) {
            return categoryRepository.save(
                    Category.builder()
                            .name(categoryName)
                            .build()
            );
        }
        return category;
    }

    private Options findByTypeOption (String typeOption, String cream, String finition, String paste) {
        Options options = optionsRepository.findByTypeOption(typeOption)
                .orElse(null);

        if (options == null){
            return optionsRepository.save(
                        Options.builder()
                                .typeOption(typeOption)
                                .cream(cream)
                                .finition(finition)
                                .paste(paste)
                                .build()
            );
        }
        return options;
    }

}
