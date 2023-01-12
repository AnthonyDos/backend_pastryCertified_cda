package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {

    Optional<Salary> findByEmail(String email);

    @Query(value = "SELECT * FROM salary INNER JOIN role ON salary.role_id = role.id WHERE salary.id = :id", nativeQuery = true)
    Optional<Salary> findAdminById(Integer id);

}
