package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.models.Shop;
import org.springframework.web.multipart.MultipartFile;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface ShopService extends AbstractService<ShopDto>{


    Shop save(String name, String description, MultipartFile image);
}
