package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.config.JwtUtils;
import com.pastrycertified.cda.controllers.AddressController;
import com.pastrycertified.cda.dto.AddressDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.models.User;
import com.pastrycertified.cda.repository.AddressRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.repository.UserRepository;
import com.pastrycertified.cda.services.UserService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;

    private final AddressRepository addressRepository;
    private final AddressServiceImpl addressServiceImpl;
    private final AddressController addressController;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return null;
    }

    @Override
    public UserDto findUserById(Integer id) {
        return userRepository.findUserById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur n'a été trouvé avec l'ID fourni :" + id));
    }

    @Override
    public Integer updateByid(Integer id, UserDto dto) {
        User ifUser = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur n'a été trouvé avec l'ID fourni :" + id));

        if ( dto.getEmail() != null ) {
            ifUser.setEmail(dto.getEmail());
        }

        if ( dto.getBirth_day() != null ) {
            ifUser.setBirth_day(dto.getBirth_day());
        }

        if ( dto.getCivility() != null ) {
            ifUser.setCivility(dto.getCivility());
        }

        if ( dto.getFirstname() != null ) {
            ifUser.setFirstname(dto.getFirstname());
        }

        if ( dto.getLastname() != null ) {
            ifUser.setLastname(dto.getLastname());
        }

        if ( dto.getPassword() != null ) {
            ifUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if ( dto.getPhone() != null ) {
            ifUser.setPhone(dto.getPhone());
        }
        userRepository.save(ifUser);
        return ifUser.getId();
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AuthenticationResponse  register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        //addressController.save(AddressDto adressDto);
        //Address address2 =
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));

      //  user.setAddress(addressServiceImpl.save());
        var savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        final User user = userRepository.findByEmail(request.getEmail()).get();
        System.out.println(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getFirstname() + " " + user.getLastname());
        final String token = jwtUtils.generateToken(user, claims);
        System.out.println(token);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(UserServiceImpl.ROLE_USER)
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
}
