package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    //Integer User addAddressUser(Integer id);

    @Query(value = "SELECT * FROM user INNER JOIN role ON user.role_id = role.id WHERE user.id = :id", nativeQuery = true)
    Optional<User> findUserById(Integer id);
}
//SELECT * FROM `user` inner join role ON user.role_id = role.id WHERE user.id = 1;