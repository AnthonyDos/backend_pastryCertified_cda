package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.*;
import com.pastrycertified.cda.services.SalaryService;
import com.pastrycertified.cda.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.auth}")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final SalaryService salaryService;

    @PostMapping("${auth.register}")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserDto user
    ) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("${auth.authenticate}")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
            ) {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PostMapping("${auth.register.admin}")
    public ResponseEntity<AuthenticationResponse> registerAdmin(
            @RequestBody SalaryDto salary
    ) {
        return ResponseEntity.ok(salaryService.register(salary));
    }

    @PostMapping("${auth.authenticate.admin}")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(salaryService.authenticate(request));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("${auth.register.pastryChef}")
    public ResponseEntity<Integer> savePastryChef(
            @RequestBody SalaryDto salaryDto
    ) {
        return ResponseEntity.ok(salaryService.savePastryChef(salaryDto));
    }

    @PostMapping("${auth.authenticate.pastyChef}")
    public ResponseEntity<AuthenticationResponse> registerPastryChef(
            @RequestBody SalaryDto salary
    ) {
        return ResponseEntity.ok(salaryService.registerPastryChef(salary));
    }
}
