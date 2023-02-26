package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.ProductsDto;
import com.pastrycertified.cda.models.Products;
import org.springframework.web.multipart.MultipartFile;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface ProductsService extends AbstractService<ProductsDto>{
    Products save(String name, String ingredients, String price, MultipartFile image, String categoryName, String typeOption, String cream, String finition, String paste);
}
