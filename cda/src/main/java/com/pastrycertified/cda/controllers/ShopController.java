package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.models.Shop;
import com.pastrycertified.cda.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/")
    public Shop save(
            @RequestPart("name") String name,
            @RequestPart("descripion") String description,
            @RequestPart("image") MultipartFile image
            ) {
        return shopService.save(name, description, image);
    }
}
