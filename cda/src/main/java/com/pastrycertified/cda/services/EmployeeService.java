package com.pastrycertified.cda.services;

import com.pastrycertified.cda.dto.EmployeeDto;
import com.pastrycertified.cda.dto.AuthenticationRequest;
import com.pastrycertified.cda.dto.AuthenticationResponse;

/**
 * cette interface intéragie entre le controller et le service d'implémentation
 */
public interface EmployeeService extends AbstractService<EmployeeDto>{

    AuthenticationResponse register(EmployeeDto admin);

    AuthenticationResponse registerPastryChef(EmployeeDto admin);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    EmployeeDto findAdminById(Integer id);

    Integer savePastryChef(EmployeeDto dto);
}
