package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.OptionsDto;
import com.pastrycertified.cda.models.Options;
import com.pastrycertified.cda.models.Shop;
import com.pastrycertified.cda.services.OptionsService;
import com.pastrycertified.cda.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionsController {

    private final OptionsService optionsService;
    private final ProductsService productsService;

    @PostMapping("/")
    public ResponseEntity<Options> save(
            @RequestBody OptionsDto options
    ) {
        System.out.println(options);
        return ResponseEntity.ok(optionsService.save(options));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OptionsDto>>findAll() { return ResponseEntity.ok(optionsService.findAll());}
}
