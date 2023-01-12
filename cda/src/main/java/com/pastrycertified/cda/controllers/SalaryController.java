package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.SalaryDto;
import com.pastrycertified.cda.services.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody SalaryDto salaryDto
            ) {
        return ResponseEntity.ok(service.save(salaryDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<SalaryDto>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @PreAuthorize("hasRole('ROLE_PASTRYCHEF') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{salary-id}")
    public ResponseEntity<SalaryDto>findById(
            @PathVariable("salary-id") Integer salaryId
    ) {
        return ResponseEntity.ok(service.findAdminById(salaryId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("/update/{salary-id}")
    public ResponseEntity<Integer> update(
            @PathVariable("salary-id") Integer salaryId,
            @RequestBody SalaryDto salaryDto
    ) {
        return ResponseEntity.ok(service.updateByid(salaryId, salaryDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{salary-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("salary-id") Integer salaryId
    ) {
        service.delete(salaryId);
        return ResponseEntity.accepted().build();
    }
}
