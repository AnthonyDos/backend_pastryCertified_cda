package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.ShopDto;
import com.pastrycertified.cda.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping(value = "/shopDto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer>save(
    //public ResponseEntity<ShopDto>save(
            @RequestPart("shopDto") ShopDto shopDto
           // @RequestPart("image") MultipartFile image
            ) {
        System.out.println(shopDto);

        return ResponseEntity.ok(shopService.save(shopDto));
        //return ResponseEntity.status(HttpStatus.OK).body(shopService.save(saveShop, image));
    }
}
