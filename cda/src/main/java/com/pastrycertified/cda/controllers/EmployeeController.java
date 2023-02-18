package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.EmployeeDto;
import com.pastrycertified.cda.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url.employees}")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("${create.employee}")
    public ResponseEntity<Integer> save(
            @RequestBody EmployeeDto employeeDto
            ) {
        return ResponseEntity.ok(service.save(employeeDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("${all.employees}")
    public ResponseEntity<List<EmployeeDto>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @PreAuthorize("hasRole('ROLE_PASTRYCHEF') or hasRole('ROLE_ADMIN')")
    @GetMapping("${employee.id}")
    public ResponseEntity<EmployeeDto>findById(
            @PathVariable("employee-id") Integer employeeId
    ) {
        return ResponseEntity.ok(service.findAdminById(employeeId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping("${update.employee}")
    public ResponseEntity<Integer> update(
            @PathVariable("employee-id") Integer employeeId,
            @RequestBody EmployeeDto employeeDto
    ) {
        return ResponseEntity.ok(service.updateByid(employeeId, employeeDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("${delete.employee}")
    public ResponseEntity<Void> delete(
            @PathVariable("employee-id") Integer employeeId
    ) {
        service.delete(employeeId);
        return ResponseEntity.accepted().build();
    }
}
