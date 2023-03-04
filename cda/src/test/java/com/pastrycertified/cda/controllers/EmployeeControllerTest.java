package com.pastrycertified.cda.controllers;

import com.pastrycertified.cda.config.JwtUtils;
import com.pastrycertified.cda.dto.EmployeeDto;
import com.pastrycertified.cda.models.Employee;
import com.pastrycertified.cda.repository.EmployeeRepository;
import com.pastrycertified.cda.repository.RoleRepository;
import com.pastrycertified.cda.services.EmployeeService;
import com.pastrycertified.cda.services.impl.EmployeeServiceImpl;
import com.pastrycertified.cda.validators.ObjectsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    EmployeeService employeeServiceImpl;

    @Mock
    RoleRepository roleRepository;

    @Mock
    ObjectsValidator objectsValidator;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        this.employeeServiceImpl = new EmployeeServiceImpl(employeeRepository,roleRepository,objectsValidator,passwordEncoder,jwtUtils,authenticationManager);
    }
    @Test
    void save() {
    }

    @Test
    @DisplayName("should return list employees")
    void findAll() {

        EmployeeDto mockEmployeeDto = new EmployeeDto(1,"lastname","firstname","test@gmail.com","password","0000000000","00A2525","pastry_chef");
        EmployeeDto mockEmployeeDto2 = new EmployeeDto(2,"lastname2","firstname2","test2@gmail.com","password2","0000000000","00A25252","pastry_chef");
        EmployeeDto mockEmployeeDto3 = new EmployeeDto(3,"lastname3","firstname3","test3@gmail.com","password3","0000000000","00A25253","pastry_chef");

        List<EmployeeDto>employeeDtoList =new ArrayList<>();
        employeeDtoList.add(mockEmployeeDto);
        employeeDtoList.add(mockEmployeeDto2);
        employeeDtoList.add(mockEmployeeDto3);

        when(employeeServiceImpl.findAll()).thenReturn(employeeDtoList);
        List<Employee>returnResponseEmployee = employeeRepository.findAll();

        Assertions.assertNotNull(returnResponseEmployee);
        Assertions.assertEquals(3,returnResponseEmployee.size());
    }

    @Test
    @DisplayName("should return one employee")
    void findAdminById() {

        EmployeeDto mockEmployeeDto = new EmployeeDto(136,"lastname","firstname","test@gmail.com","password","0000000000","admin0586","admin");

        when(employeeServiceImpl.findAdminById(136)).thenReturn(mockEmployeeDto);
        Optional<Employee>returnResponseEmployee = employeeRepository.findAdminById(136);

        Assertions.assertEquals(1,returnResponseEmployee.get().getId());
        Assertions.assertEquals("lastname",returnResponseEmployee.get().getLastname());
        Assertions.assertEquals("firstname",returnResponseEmployee.get().getFirstname());
        Assertions.assertEquals("test@gmail.com",returnResponseEmployee.get().getEmail());
        Assertions.assertNotNull(returnResponseEmployee);
    }

    @Test
    void update() {
    }
}