package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * cette interface intéragie entre la bdd et le service d'implémentation
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String roleName);
}
