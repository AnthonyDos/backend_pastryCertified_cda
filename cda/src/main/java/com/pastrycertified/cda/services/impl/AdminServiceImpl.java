package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.config.JwtUtils;
import com.pastrycertified.cda.dto.AdminDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.models.Admin;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.repository.AdminRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.repository.UserRepository;
import com.pastrycertified.cda.services.AdminService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<AdminDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    private static final String ACTIVITY = "admin";
    private static final String NUMBERS = "0123456789";

    @Override
    public Integer save(AdminDto dto) {
        validator.validate(dto);
        Admin admin = AdminDto.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(findOrCreateRole(ROLE_ADMIN));
        return adminRepository.save(admin).getId();
    }

    @Override
    public List<AdminDto> findAll() {
        return adminRepository.findAll()
                .stream()
                .map(AdminDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto findById(Integer id) {
        return null;
    }

    @Override
    public Integer updateByid(Integer id, AdminDto dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(AdminServiceImpl.ROLE_ADMIN)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }

    @Override
    @Transactional
    public AuthenticationResponse register(AdminDto dto) {
        validator.validate(dto);
        Admin admin = AdminDto.toEntity(dto);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole(findOrCreateRole(ROLE_ADMIN));
        admin.setCast_member(ACTIVITY + generateCastNumber(4));

        var savedAdmin = adminRepository.save(admin);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedAdmin.getId());
        claims.put("fullName", savedAdmin.getFirstname() + " " + savedAdmin.getLastname());
        String token = jwtUtils.generateToken(savedAdmin, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request);
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final Admin admin = adminRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        System.out.println(claims);
        claims.put("userId", admin.getId());
        claims.put("fullName", admin.getFirstname() + " " + admin.getLastname());
        final String token = jwtUtils.generateToken(admin, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AdminDto findAdminById(Integer id) {

        return adminRepository.findAdminById(id)
                .map(AdminDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun admin n'a été trouvé avec l'ID fourni :" + id));
    }

    private static String generateCastNumber(int length) {

        String numericStr = NUMBERS;
        StringBuilder castNumber = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
           int randomCastNumber = (int)(numericStr.length() * Math.random());
            castNumber.append(numericStr.charAt(randomCastNumber));
        }

        return castNumber.toString();
    }
}
