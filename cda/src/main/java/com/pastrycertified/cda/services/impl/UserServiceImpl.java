package com.pastrycertified.cda.services.impl;

//import com.pastrycertified.cda.dto.AuthenticationResponse;
import com.pastrycertified.cda.dto.UserDto;
        import com.pastrycertified.cda.models.Role;
import com.pastrycertified.cda.models.User;
        import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.repository.UserRepository;
import com.pastrycertified.cda.services.UserService;
import com.pastrycertified.cda.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
        import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;

//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ObjectsValidator<UserDto> validator;
    //private final PasswordEncoder passwordEncoder;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(user.getPassword());
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
        return userRepository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun utilisateur n'a été trouvé avec l'ID fourni :" + id));
    }

    @Override
    public Integer updateByid(Integer id, UserDto dto) {
        User user2 = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Aucun utilisateur n'a été trouvé avec l'ID fourni :" + id));

        if ( dto.getEmail() != null ) {
            user2.setEmail(dto.getEmail());
        }

        if ( dto.getBirth_day() != null ) {
            user2.setBirth_day(dto.getBirth_day());
        }

        if ( dto.getCivility() != null ) {
            user2.setCivility(dto.getCivility());
        }

        if ( dto.getFirstname() != null ) {
            user2.setFirstname(dto.getFirstname());
        }

        if ( dto.getLastname() != null ) {
            user2.setLastname(dto.getLastname());
        }

        if ( dto.getPassword() != null ) {
            user2.setPassword(dto.getPassword());
        }

        if ( dto.getPhone() != null ) {
            user2.setPhone(dto.getPhone());
        }
        userRepository.save(user2);
        return user2.getId();
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
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
