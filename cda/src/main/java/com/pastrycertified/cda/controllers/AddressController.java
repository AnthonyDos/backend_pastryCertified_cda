package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.services.AddressService;
import com.pastrycertified.cda.services.impl.AddressServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/address-user/{user-id}")
    public ResponseEntity<AddressDto> findAddressByIdUser(
            @PathVariable("user-id") Integer id
    ) {
        return ResponseEntity.ok(addressService.findAddressByIdUser(id));
    }

    @GetMapping("/{address-id}")
    public ResponseEntity<AddressDto> findById(
            @PathVariable("address-id") Integer id
    ) {
        return ResponseEntity.ok(addressService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<AddressDto>> findAll() { return ResponseEntity.ok(addressService.findAll());}

    @PatchMapping("/update-address/{address-id}")
    public ResponseEntity<Integer> updateAddress(
            @PathVariable("address-id") Integer id,
            @RequestBody AddressDto dto
    ) {
        return ResponseEntity.ok(addressService.updateByid(id, dto));
    }

    @DeleteMapping("/{address-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("address-id") Integer id
    ) {
        addressService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
