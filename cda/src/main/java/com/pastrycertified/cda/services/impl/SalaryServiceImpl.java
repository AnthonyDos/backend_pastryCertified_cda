package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.config.JwtUtils;
import com.pastrycertified.cda.dto.SalaryDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.models.Salary;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.repository.SalaryRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.services.SalaryService;
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
public class SalaryServiceImpl implements SalaryService {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_PASTRYCHEF = "ROLE_PASTRYCHEF";

    private static final String ACTIVITY = "admin";
    private static final String ACTIVITY_PASTRYCHEF = "chef";

    private static final String NUMBERS = "0123456789";

    private final SalaryRepository salaryRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<SalaryDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;


    @Override
    public Integer save(SalaryDto dto) {
        validator.validate(dto);
        Salary salary = SalaryDto.toEntity(dto);
        salary.setPassword(passwordEncoder.encode(salary.getPassword()));
        salary.setRole(findOrCreateRole(ROLE_ADMIN));
        return salaryRepository.save(salary).getId();
    }

    @Override
    public Integer savePastryChef(SalaryDto dto) {
        validator.validate(dto);
        Salary salary = SalaryDto.toEntity(dto);
        salary.setPassword(passwordEncoder.encode(salary.getPassword()));
        salary.setRole(findOrCreateRole(ROLE_PASTRYCHEF));
        return salaryRepository.save(salary).getId();
    }

    @Override
    public List<SalaryDto> findAll() {
        return salaryRepository.findAll()
                .stream()
                .map(SalaryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SalaryDto findById(Integer id) {
        return null;
    }

    @Override
    public Integer updateByid(Integer id, SalaryDto dto) {
        Salary ifSalary = salaryRepository.findAdminById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucun admin correspond à l'id: " + id));
        if ( dto.getLastname() != null ) {
            ifSalary.setLastname(dto.getLastname());
        }
        if ( dto.getFirstname() != null ) {
            ifSalary.setFirstname(dto.getFirstname());
        }
        if ( dto.getEmail() != null ) {
            ifSalary.setEmail(dto.getEmail());
        }
        if ( dto.getPassword() != null ) {
            ifSalary.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if ( dto.getPhone() != null ) {
            ifSalary.setPhone(dto.getPhone());
        }
        salaryRepository.save(ifSalary);
        return ifSalary.getId();
    }

    @Override
    public void delete(Integer id) {
        salaryRepository.findAdminById(id)
                .orElseThrow(()-> new EntityNotFoundException("La suppression à échoué aucun admin avec l'id: " + id));
        salaryRepository.deleteById(id);
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
    public AuthenticationResponse register(SalaryDto dto) {
        validator.validate(dto);
        Salary salary = SalaryDto.toEntity(dto);
        salary.setPassword(passwordEncoder.encode(salary.getPassword()));
        salary.setRole(findOrCreateRole(ROLE_ADMIN));
        salary.setCast_member(ACTIVITY + generateCastNumber(4));

        var savedAdmin = salaryRepository.save(salary);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedAdmin.getId());
        claims.put("fullName", savedAdmin.getFirstname() + " " + savedAdmin.getLastname());
        String token = jwtUtils.generateToken(savedAdmin, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    @Transactional
    public AuthenticationResponse registerPastryChef(SalaryDto dto) {
        validator.validate(dto);
        Salary salary = SalaryDto.toEntity(dto);
        salary.setPassword(passwordEncoder.encode(salary.getPassword()));
        salary.setRole(findOrCreateRole(ROLE_PASTRYCHEF));
        salary.setCast_member(ACTIVITY_PASTRYCHEF + generateCastNumber(4));

        var savedAdmin = salaryRepository.save(salary);
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

        final Salary salary = salaryRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        System.out.println(claims);
        claims.put("userId", salary.getId());
        claims.put("fullName", salary.getFirstname() + " " + salary.getLastname());
        final String token = jwtUtils.generateToken(salary, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public SalaryDto findAdminById(Integer id) {

        return salaryRepository.findAdminById(id)
                .map(SalaryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun admin n'a été trouvé avec l'ID fourni :" + id));
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
