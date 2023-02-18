package com.pastrycertified.cda.services.auth;

import com.pastrycertified.cda.repository.EmployeeRepository;
import com.pastrycertified.cda.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (!userRepository.findByEmail(email).isEmpty()) {
            return userRepository.findByEmail(email)
                    .orElseThrow(()-> new EntityNotFoundException("Pas d'utilisateur trouvé provenant de cette email"));
        }

        if (!employeeRepository.findByEmail(email).isEmpty()) {
            return employeeRepository.findByEmail(email)
                    .orElseThrow(()-> new EntityNotFoundException("Pas de salarié trouvé provenant de cette email"));
        }
        return employeeRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("aucun user trouvé provenant de cette email"));
    }
}

