package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody UserDto userDto
            ) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findUserById(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findUserById(userId));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/update/{user-id}")
    public ResponseEntity<Integer> updateAccount(
            @PathVariable("user-id") Integer userId,
            @RequestBody UserDto user
    ) {
        return ResponseEntity.ok(service.updateByid(userId,user));
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }
}
