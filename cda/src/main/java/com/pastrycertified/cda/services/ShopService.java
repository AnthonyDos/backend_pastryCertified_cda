package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.models.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService extends AbstractService<ShopDto>{


    Shop save(String name, String description, MultipartFile image);
}
