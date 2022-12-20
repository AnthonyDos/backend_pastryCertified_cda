package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AddressDto addressDto
            ) {
        return ResponseEntity.ok(addressService.save(addressDto));
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findAddressByIdUser(
            @PathVariable("address-id") Integer id
    ) {
        return ResponseEntity.ok(addressService.findAddressByIdUser(id));
    }
}
