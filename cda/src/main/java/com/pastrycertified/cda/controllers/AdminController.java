package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AdminDto;
import com.pastrycertified.cda.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody AdminDto adminDto
            ) {
        return ResponseEntity.ok(service.save(adminDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<AdminDto>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/{admin-id}")
    public ResponseEntity<AdminDto>findById(
            @PathVariable("admin-id") Integer adminId
    ) {
        return ResponseEntity.ok(service.findAdminById(adminId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/update/{admin-id}")
    public ResponseEntity<Integer> update(
            @PathVariable("admin-id") Integer adminId,
            @RequestBody AdminDto adminDto
    ) {
        return ResponseEntity.ok(service.updateByid(adminId, adminDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{admin-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("admin-id") Integer adminId
    ) {
        service.delete(adminId);
        return ResponseEntity.accepted().build();
    }
}
