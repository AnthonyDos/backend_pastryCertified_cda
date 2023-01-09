package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AdminDto;
import com.pastrycertified.cda.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AdminDto adminDto
            ) {
        return ResponseEntity.ok(service.save(adminDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<AdminDto>> findAll() { return ResponseEntity.ok(service.findAll()); }
}
