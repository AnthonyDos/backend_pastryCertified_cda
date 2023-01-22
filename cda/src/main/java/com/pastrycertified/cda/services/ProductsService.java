package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.ProductsDto;
import com.pastrycertified.cda.models.Products;
import org.springframework.web.multipart.MultipartFile;

public interface ProductsService extends AbstractService<ProductsDto>{
    Products save(String name, String ingredients, String price, MultipartFile image, String categoryName);
}
