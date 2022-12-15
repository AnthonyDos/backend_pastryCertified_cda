package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
