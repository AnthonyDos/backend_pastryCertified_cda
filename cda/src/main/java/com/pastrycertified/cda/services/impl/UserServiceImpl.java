package com.pastrycertified.cda.services.impl;

import com.pastrycertified.cda.dto.AddressDto;
//import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.UserDto;
import com.pastrycertified.cda.models.Address;
import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.models.User;
import com.pastrycertified.cda.repository.AddressRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.repository.UserRepository;
import com.pastrycertified.cda.services.UserService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(user.getPassword());
        user.setRole(findOrCreateRole(ROLE_USER));
        return userRepository.save(user).getId();
    }

//    @Override
//    @Transactional
//    public AuthenticationResponse register(UserDto dto) {
//        validator.validate(dto);
//        User user = UserDto.toEntity(dto);
//        user.setPassword(user.getPassword());
//        user.setRole(findOrCreateRole(ROLE_USER));
//
//        return AuthenticationResponse.builder()
//                .;
//    }

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
