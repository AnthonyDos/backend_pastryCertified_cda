package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.models.Shop;
import com.pastrycertified.cda.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${url.shops}")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "${create.shop}")
    public Shop save(
            @RequestPart("name") String name,
            @RequestPart("description") String description,
            @RequestPart("image") MultipartFile image
            ) {
        return shopService.save(name, description, image);
    }

    @GetMapping("${all.shop}")
    public ResponseEntity<List<ShopDto>>findAll() { return ResponseEntity.ok(shopService.findAll());}

    @GetMapping("${shop.id}")
    public ResponseEntity<ShopDto>findShopById(
            @PathVariable("shop-id") Integer shopId
    ) {
        return ResponseEntity.ok(shopService.findById(shopId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("${update.shop.id}")
    public ResponseEntity<Integer>updateShop(
            @PathVariable("shop-id") Integer shopId,
            @RequestBody ShopDto shop
    ) {
        return ResponseEntity.ok(shopService.updateByid(shopId, shop));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("${delete.shop.id}")
    public ResponseEntity<Void> delete(
            @PathVariable("shop-id") Integer shopId
    ) {
        shopService.delete(shopId);
        return ResponseEntity.accepted().build();
    }

}
