package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    @Query(value = "SELECT * FROM admin INNER JOIN role ON admin.role_id = role.id WHERE admin.id = :id", nativeQuery = true)
    Optional<Admin> findAdminById(Integer id);

}
