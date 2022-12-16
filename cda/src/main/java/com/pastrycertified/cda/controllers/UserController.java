package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.models.User;
import com.pastrycertified.cda.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

//    @PostMapping("/")
//    public ResponseEntity<Integer> save(
//            @RequestBody UserDto userDto
//            ) {
//        return ResponseEntity.ok(service.save(userDto));
//    }

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
        System.out.println(user);
        return ResponseEntity.ok(service.updateByid(userId,user));
    }
}
