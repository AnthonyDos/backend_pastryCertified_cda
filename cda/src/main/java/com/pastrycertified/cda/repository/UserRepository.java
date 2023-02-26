package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * cette interface intéragie entre la bdd et le service d'implémentation
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM user INNER JOIN role ON user.role_id = role.id WHERE user.id = :id", nativeQuery = true)
    Optional<User> findUserById(Integer id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET address_id= :addressId WHERE user.id = :id", nativeQuery = true)
    void updateByIdUser(Integer addressId, Integer id);
}