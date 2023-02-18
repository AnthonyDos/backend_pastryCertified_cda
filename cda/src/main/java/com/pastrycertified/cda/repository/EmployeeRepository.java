package com.pastrycertified.cda.repository;

import com.pastrycertified.cda.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    @Query(value = "SELECT * FROM salary INNER JOIN role ON salary.role_id = role.id WHERE salary.id = :id", nativeQuery = true)
    Optional<Employee> findAdminById(Integer id);

}
