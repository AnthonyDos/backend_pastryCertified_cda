package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.*;
import com.pastrycertified.cda.services.EmployeeService;
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
    private final EmployeeService employeeService;

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
            @RequestBody EmployeeDto salary
    ) {
        return ResponseEntity.ok(employeeService.register(salary));
    }

    @PostMapping("${auth.authenticate.admin}")
    public ResponseEntity<AuthenticationResponse> authenticateAdmin(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(employeeService.authenticate(request));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("${auth.register.pastryChef}")
    public ResponseEntity<Integer> savePastryChef(
            @RequestBody EmployeeDto employeeDto
    ) {
        return ResponseEntity.ok(employeeService.savePastryChef(employeeDto));
    }

    @PostMapping("${auth.authenticate.pastyChef}")
    public ResponseEntity<AuthenticationResponse> registerPastryChef(
            @RequestBody EmployeeDto salary
    ) {
        return ResponseEntity.ok(employeeService.registerPastryChef(salary));
    }
}
