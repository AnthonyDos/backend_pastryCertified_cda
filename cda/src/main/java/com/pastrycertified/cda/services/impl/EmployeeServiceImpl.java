package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.config.JwtUtils;
import com.pastrycertified.cda.dto.EmployeeDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.models.Employee;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.repository.EmployeeRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.services.EmployeeService;
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

/**
 * implémentation des services employées, vérification des données envoyés
 * en bdd et gestion des services inscription et connexion et qui implémente le service
 */
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_PASTRYCHEF = "ROLE_PASTRYCHEF";

    private static final String ACTIVITY = "admin";
    private static final String ACTIVITY_PASTRYCHEF = "chef";

    private static final String NUMBERS = "0123456789";

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<EmployeeDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private Integer LENGTH_CAST_NUMBER = 4;


    @Override
    public Integer save(EmployeeDto dto) {
        validator.validate(dto);
        Employee employee = EmployeeDto.toEntity(dto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(findOrCreateRole(ROLE_ADMIN));
        return employeeRepository.save(employee).getId();
    }

    @Override
    public Integer savePastryChef(EmployeeDto dto) {
        validator.validate(dto);
        Employee employee = EmployeeDto.toEntity(dto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(findOrCreateRole(ROLE_PASTRYCHEF));
        return employeeRepository.save(employee).getId();
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findById(Integer id) {
        return null;
    }

    @Override
    public EmployeeDto findAdminById(Integer id) {

        return employeeRepository.findAdminById(id)
                .map(EmployeeDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun admin n'a été trouvé avec l'ID fourni :" + id));
    }

    @Override
    public Integer updateByid(Integer id, EmployeeDto dto) {
        Employee ifEmployee = employeeRepository.findAdminById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucun admin correspond à l'id: " + id));
        if ( dto.getLastname() != null ) {
            ifEmployee.setLastname(dto.getLastname());
        }
        if ( dto.getFirstname() != null ) {
            ifEmployee.setFirstname(dto.getFirstname());
        }
        if ( dto.getEmail() != null ) {
            ifEmployee.setEmail(dto.getEmail());
        }
        if ( dto.getPassword() != null ) {
            ifEmployee.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if ( dto.getPhone() != null ) {
            ifEmployee.setPhone(dto.getPhone());
        }
        employeeRepository.save(ifEmployee);
        return ifEmployee.getId();
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.findAdminById(id)
                .orElseThrow(()-> new EntityNotFoundException("La suppression à échoué aucun admin avec l'id: " + id));
        employeeRepository.deleteById(id);
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
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
    public AuthenticationResponse register(EmployeeDto dto) {
        validator.validate(dto);
        Employee employee = EmployeeDto.toEntity(dto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(findOrCreateRole(ROLE_ADMIN));
        employee.setCast_member(ACTIVITY + generateCastNumber(LENGTH_CAST_NUMBER));

        var savedAdmin = employeeRepository.save(employee);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedAdmin.getId());
        claims.put("fullName", savedAdmin.getFirstname() + " " + savedAdmin.getLastname());
        String token = jwtUtils.generateToken(savedAdmin, claims);
        final String expirationToken = String.valueOf(jwtUtils.extractExpiration(token));
        return AuthenticationResponse.builder()
                .access_token(token)
                .expiration(expirationToken)
                .build();
    }

    @Override
    @Transactional
    public AuthenticationResponse registerPastryChef(EmployeeDto dto) {
        validator.validate(dto);
        Employee employee = EmployeeDto.toEntity(dto);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRole(findOrCreateRole(ROLE_PASTRYCHEF));
        employee.setCast_member(ACTIVITY_PASTRYCHEF + generateCastNumber(LENGTH_CAST_NUMBER));

        var savedAdmin = employeeRepository.save(employee);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedAdmin.getId());
        claims.put("fullName", savedAdmin.getFirstname() + " " + savedAdmin.getLastname());
        String token = jwtUtils.generateToken(savedAdmin, claims);
        final String expirationToken = String.valueOf(jwtUtils.extractExpiration(token));
        return AuthenticationResponse.builder()
                .access_token(token)
                .expiration(expirationToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final Employee employee = employeeRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", employee.getId());
        claims.put("fullName", employee.getFirstname() + " " + employee.getLastname());
        final String token = jwtUtils.generateToken(employee, claims);
        final String expirationToken = String.valueOf(jwtUtils.extractExpiration(token));

        return AuthenticationResponse.builder()
                .access_token(token)
                .expiration(expirationToken)
                .build();
    }


    /**
     * Methode de générateur d'identifiant admin
     */
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
