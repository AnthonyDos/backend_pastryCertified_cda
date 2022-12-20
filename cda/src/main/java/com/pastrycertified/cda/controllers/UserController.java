package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody UserDto userDto
            ) {
        return ResponseEntity.ok(service.save(userDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() { return ResponseEntity.ok(service.findAll()); }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @PatchMapping("/update/{user-id}")
    public ResponseEntity<Integer> updateAccount(
            @PathVariable("user-id") Integer userId,
            @RequestBody UserDto user
    ) {
        return ResponseEntity.ok(service.updateByid(userId,user));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }
}
